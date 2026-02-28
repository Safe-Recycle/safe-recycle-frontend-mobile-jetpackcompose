package com.example.saferecycle.ui.screen.scan_waste

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saferecycle.camera.CameraPreview
import com.example.saferecycle.ui.component.LostConnectionBottomSheet
import com.example.saferecycle.ui.component.NotIdentifiedBottomSheet
import com.example.saferecycle.ui.component.PermissionDeniedBottomSheet
import com.example.saferecycle.ui.component.PermissionRationaleBottomSheet
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanWasteScreen(
    onNavigateToWasteDetailsScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    vm: ScanWasteViewModel = hiltViewModel()
) {

    val scanWasteState by vm.scanWasteState.collectAsState()

    val context = LocalContext.current
    val activity = context as Activity
    val cameraPermission = Manifest.permission.CAMERA

    val applicationContext = LocalContext.current
    var showNoInternetBottomSheet by remember { mutableStateOf(false) }
    var showNotIdentifiedBottomSheet by remember { mutableStateOf(false) }
    var showPermissionDeniedBottomSheet by remember { mutableStateOf(false) }
    var showPermissionRationaleBottomSheet by remember { mutableStateOf(false) }

    var hasPermission by remember {
        mutableStateOf(
            checkSelfPermission(
                context,
                cameraPermission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->

        hasPermission = granted

        if (!granted) {

            val shouldShowRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    cameraPermission
                )

            if (shouldShowRationale) {
                // Denial pertama
                showPermissionRationaleBottomSheet =
                    true // tampilkan bottomsheet edukasi
            } else {
                // Don't ask again
                showPermissionDeniedBottomSheet = true // bottomsheet versi "Go to Settings"
                showPermissionRationaleBottomSheet = false
            }
        }
    }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(cameraPermission)
        }
    }

    var isFlashOn by remember { mutableStateOf(false) }
    val controller = remember {
        LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
    }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            Log.d("Camera X", "Selected Picture in $selectedImageUri")
        }
    )


    LaunchedEffect(selectedImageUri) {
        if (selectedImageUri != null) {
            val multipart =
                selectedImageUri!!.toMultipart(applicationContext, "file")
            vm.scanWaste(multipart)
        }
    }

    LaunchedEffect(scanWasteState) {
        when (scanWasteState) {
            is UiState.Success -> {
                val data = (scanWasteState as UiState.Success).data
                onNavigateToWasteDetailsScreen(data.id!!)
                vm.clearState()
                Log.d("SCAN SUCCESS", "${data.id}")
            }

            is UiState.Error -> {
                selectedImageUri = null
                val error = (scanWasteState as UiState.Error).error
                when (error) {
                    is AppError.Network -> showNoInternetBottomSheet = true
                    is AppError.NotFound -> showNotIdentifiedBottomSheet = true
                    else -> {
                        showNotIdentifiedBottomSheet = true

                    }
                }
            }

            is UiState.Loading -> {}
            else -> {}
        }
    }


    Scaffold(
        topBar = {
            TopBar(
                text = "Scan Waste",
                onBackClick = { onBackClick() },
                containerColors = Color.Transparent,
                titleContentColor = SafeRecycleTheme.colors.elementBackground,
                navigationIconContentColor = SafeRecycleTheme.colors.elementBackground
            )
        },
        bottomBar = {

        }

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                controller = controller,
            )
            CameraOverlay()
            ScanWasteBottomBar(
                modifier = Modifier.padding(bottom = 36.dp),
                isFlashOn = isFlashOn,
                onFlashButtonClick = {
                    isFlashOn = !isFlashOn
                    controller.enableTorch(isFlashOn)
                },
                onShutterButtonClick = {
                    takeSavePhoto(
                        controller = controller,
                        onPhotoTaken = { uri ->
                            Log.d(
                                "Camera X",
                                "Saved Picture in $uri"
                            )
                            val multipart =
                                uri.toMultipart(applicationContext, "file")
                            vm.scanWaste(multipart)
                        },
                        applicationContext = applicationContext
                    )
                },
                onSelectImageButtonClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )
            if (scanWasteState is UiState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = SafeRecycleTheme.colors.elementBackground
                    )
                }
            }
        }
    }

    if (showNoInternetBottomSheet) {
        LostConnectionBottomSheet(
            onTryAgainClick = { showNoInternetBottomSheet = false },
            onDismissRequest = { showNoInternetBottomSheet = false }
        )
    }
    if (showNotIdentifiedBottomSheet) {
        NotIdentifiedBottomSheet(
            onTryAgainClick = { showNotIdentifiedBottomSheet = false },
            onDismissRequest = { showNotIdentifiedBottomSheet = false }
        )
    }
    if (showPermissionDeniedBottomSheet) {
        PermissionDeniedBottomSheet(
            onGoToSettingsClick = {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context.packageName, null)
                )
                context.startActivity(intent)
            },
            onDismissRequest = { showPermissionDeniedBottomSheet = false },
        )
    }

    if (showPermissionRationaleBottomSheet) {
        PermissionRationaleBottomSheet(
            onDisplayRationale = { permissionLauncher.launch(cameraPermission) },
            onDismissRequest = { showPermissionRationaleBottomSheet = false },
        )
    }
}

fun createImageFile(context: Context): File {
    val timestamp = System.currentTimeMillis()
    return File(context.cacheDir, "waste_$timestamp.jpg")
}

private fun takeSavePhoto(
    controller: LifecycleCameraController,
    onPhotoTaken: (Uri) -> Unit,
    applicationContext: Context
) {
    val outputFile = createImageFile(applicationContext)

    val outputOptions =
        ImageCapture.OutputFileOptions.Builder(outputFile).build()

    controller.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(applicationContext),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val uri = output.savedUri ?: outputFile.toUri()
                // ⬇️ INI YANG NANTI KAMU KIRIM KE VIEWMODEL
                onPhotoTaken(uri)
            }

            override fun onError(exception: ImageCaptureException) {
                // handle error
                Log.e("CameraX", "Couldn't take photo", exception)
            }
        }
    )
}

fun Uri.toMultipart(context: Context, partName: String): MultipartBody.Part {
    val contentResolver = context.contentResolver

    val fileName = "upload_${System.currentTimeMillis()}.jpg"
    val tempFile = File(context.cacheDir, fileName)

    contentResolver.openInputStream(this)?.use { inputStream ->
        tempFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }

    val requestFile = tempFile
        .asRequestBody("image/jpeg".toMediaType())

    return MultipartBody.Part.createFormData(
        partName,
        tempFile.name,
        requestFile
    )
}
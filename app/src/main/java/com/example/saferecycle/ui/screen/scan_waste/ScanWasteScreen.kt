package com.example.saferecycle.ui.screen.scan_waste

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.saferecycle.camera.CameraPreview
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import java.io.File
import java.util.concurrent.Executor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanWasteScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    val applicationContext = LocalContext.current
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
        onResult = {
            uri -> selectedImageUri = uri
            Log.d("Camera X" ,"Selected Picture in $selectedImageUri")
        }
    )

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

    ) { innerPadding ->
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
        }
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
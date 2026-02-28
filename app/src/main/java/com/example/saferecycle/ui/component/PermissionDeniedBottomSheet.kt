package com.example.saferecycle.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.saferecycle.R
import com.example.saferecycle.ui.theme.SafeRecycleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionDeniedBottomSheet(
    modifier: Modifier = Modifier,
    onGoToSettingsClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        containerColor = SafeRecycleTheme.colors.background,
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
        dragHandle = {}
    ) {
        Column(
            Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            verticalArrangement = spacedBy(23.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(87.dp),
                painter = painterResource(R.drawable.no_results),
                contentDescription = "Image for Permission Denied"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = spacedBy(11.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoldedText("Camera permissions are needed")
                NormalText(text = "This feature requires camera access to work properly. Please allow camera permission to continue or pick a waste item from your gallery")
            }
            NormalButton(onClick = { onGoToSettingsClick() }, text = "Go to Settings")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionRationaleBottomSheet(
    modifier: Modifier = Modifier,
    onDisplayRationale: ()->Unit,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        containerColor = SafeRecycleTheme.colors.background,
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
        dragHandle = {}
    ) {
        Column(
            Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            verticalArrangement = spacedBy(23.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(87.dp),
                painter = painterResource(R.drawable.no_results),
                contentDescription = "Image for Permission Denied"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = spacedBy(11.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoldedText("Camera permissions are needed")
                NormalText(text = "This feature requires camera access to work properly. Please allow camera permission to continue or pick a waste item from your gallery")
            }
            NormalButton(onClick = { onDisplayRationale() }, text = "Grant Permission")
        }
    }
}
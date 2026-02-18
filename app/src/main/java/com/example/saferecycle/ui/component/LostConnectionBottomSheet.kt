package com.example.saferecycle.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.saferecycle.R
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LostConnectionBottomSheet(
    modifier: Modifier = Modifier,
    onTryAgainClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
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
                painter = painterResource(R.drawable.no_internet),
                contentDescription = "Image for Lost Connection"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = spacedBy(11.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoldedText("Lost Connection")
                NormalText(text = "No internet connection found. Please check your connection and try again")
            }
            NormalButton(onClick = { onTryAgainClick() }, text = "Try Again")
        }
    }
}
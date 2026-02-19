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
fun NotIdentifiedBottomSheet(
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
                painter = painterResource(R.drawable.no_results),
                contentDescription = "Image for Couldn't identified waste"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = spacedBy(11.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoldedText("Couldn't identify the waste")
                NormalText(text = "The photo isn’t clear enough or doesn’t appear to show a waste item. If you think this is a mistake, try retaking the photo.")
            }
            NormalButton(onClick = { onTryAgainClick() }, text = "Retake Photo")
        }
    }
}
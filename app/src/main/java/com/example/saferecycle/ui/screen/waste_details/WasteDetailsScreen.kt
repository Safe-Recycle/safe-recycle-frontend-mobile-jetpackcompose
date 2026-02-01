package com.example.saferecycle.ui.screen.waste_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.saferecycle.data.Waste
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.ui.component.CategoryCard
import com.example.saferecycle.ui.component.HorizontalLine
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.ui.theme.fontFamily
import com.example.saferecycle.R
import com.example.saferecycle.ui.component.BoldedText
import com.example.saferecycle.ui.component.NormalText
import dev.jeziellago.compose.markdowntext.MarkdownText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WasteDetailsScreen(wasteId: Int, onBackClick: () -> Unit) {
    val waste = dummyWastes[wasteId - 1]
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetPeekHeight = 500.dp,
        sheetDragHandle = {
            BottomSheetDefaults.DragHandle(
                width = 83.dp,
                color = SafeRecycleTheme.colors.foreground.copy(alpha = 0.12f)
            )
        },
        sheetContent = { SheetContent(waste = waste) },
        sheetContainerColor = SafeRecycleTheme.colors.background,
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                model = waste.imagePath,
                contentDescription = "Image for ${waste.name}"
            )
            WasteDetailsBackClickIconButton(
                modifier = Modifier.statusBarsPadding(),
                onBackClick = { onBackClick() }
            )

        }
    }
}

@Composable
private fun SheetContent(waste: Waste) {
    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 24.dp
            )
            .heightIn(min = 100.dp, max = 650.dp)
    ) {
        LazyColumn(
            verticalArrangement = spacedBy(18.dp),
        ) {
            item {
                BoldedText(text = waste.name)
            }
            item {
                NormalText(text = waste.description, textAlign = TextAlign.Start)
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    CategoryCard(category = waste.category, onClick = {})
                    WastePropertyName(
                        imageIdName = if (waste.isReusable) R.drawable.reusable else R.drawable.non_reusable,
                        contentDescription = "Image for Reusability of the waste",
                        propertyName = if (waste.isReusable) "Reusable" else "Non Reusable",
                    )
                    WastePropertyName(
                        imageIdName = if (waste.isRecyclable) R.drawable.recycleable else R.drawable.non_recycleable,
                        contentDescription = "Image for Recyclability of the waste",
                        propertyName = if (waste.isRecyclable) "Recyclable" else "Non Recyclable"
                    )
                    WastePropertyName(
                        imageIdName = if (waste.isHazardous) R.drawable.hazardous else R.drawable.non_hazardous,
                        contentDescription = "Image for security of the waste",
                        propertyName = if (waste.isHazardous) "Hazardous" else "Non Hazardous"
                    )
                }
            }
            item {
                HorizontalLine()
            }
            item {
                MarkdownText(
                    markdown = waste.recycleTips.trimIndent(),
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontSize = 12.sp,
                        letterSpacing = 16.sp
                    )
                )
            }
        }
    }
}
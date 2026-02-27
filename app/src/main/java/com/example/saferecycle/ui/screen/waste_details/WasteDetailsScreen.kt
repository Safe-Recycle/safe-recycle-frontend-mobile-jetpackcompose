package com.example.saferecycle.ui.screen.waste_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.ui.component.CategoryCard
import com.example.saferecycle.ui.component.HorizontalLine
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.ui.theme.fontFamily
import com.example.saferecycle.R
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.component.BoldedText
import com.example.saferecycle.ui.component.BoldedTextSkeleton
import com.example.saferecycle.ui.component.CategoryCardSkeleton
import com.example.saferecycle.ui.component.LostConnectionBottomSheet
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextSkeleton
import com.example.saferecycle.ui.component.SecondaryTextSkeleton
import com.example.saferecycle.ui.component.formatImageUrl
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import dev.jeziellago.compose.markdowntext.MarkdownText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WasteDetailsScreen(
    wasteId: Int, onBackClick: () -> Unit,
    vm: WasteDetailsViewModel = hiltViewModel()
) {
    val wasteDetailsState by vm.wasteDetails.collectAsState()
    val state = rememberPullToRefreshState()
    var showBottomSheet by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        vm.getWasteDetails(wasteId)
//        vm.getWasteDetailsDummy()
    }

    LaunchedEffect(wasteDetailsState) {
        if (wasteDetailsState is UiState.Error) {
            val error = (wasteDetailsState as UiState.Error).error
            if (error is AppError.Network) {
                showBottomSheet = true
            }
        }
    }

    val sheetState = rememberStandardBottomSheetState(
        skipHiddenState = false,
        initialValue = SheetValue.PartiallyExpanded,
        confirmValueChange = { newValue ->
            newValue != SheetValue.Hidden // cegah hidden
        }
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(sheetState)

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
        sheetContent = {
            when (wasteDetailsState) {
                is UiState.Loading -> SheetContentSkeleton()
                is UiState.Success -> {
                    val wasteDetails =
                        (wasteDetailsState as UiState.Success).data
                    SheetContent(waste = wasteDetails)
                }

                is UiState.Error -> {
                    SheetContentSkeleton()
                }

                else -> {}
            }
        },
        sheetContainerColor = SafeRecycleTheme.colors.background,
    ) {
        when (wasteDetailsState) {
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .padding(0.dp)
                        .background(
                            color = SafeRecycleTheme.colors.stroke,
                            shape = RectangleShape
                        )
                        .fillMaxSize()
                )
                WasteDetailsBackClickIconButton(
                    modifier = Modifier.statusBarsPadding(),
                    onBackClick = { onBackClick() }
                )
            }

            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .padding(0.dp)
                        .background(
                            color = SafeRecycleTheme.colors.stroke,
                            shape = RectangleShape
                        )
                        .size(500.dp)
                        .fillMaxWidth()
                )
                WasteDetailsBackClickIconButton(
                    modifier = Modifier.statusBarsPadding(),
                    onBackClick = { onBackClick() }
                )
            }

            is UiState.Success -> {
                val wasteDetails =
                    (wasteDetailsState as UiState.Success).data
                Box {
                    AsyncImage(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth(),
                        model = formatImageUrl(wasteDetails.imagePath),
                        contentDescription = "Image for ${wasteDetails.name}"
                    )
                    WasteDetailsBackClickIconButton(
                        modifier = Modifier.statusBarsPadding(),
                        onBackClick = { onBackClick() }
                    )
                }
            }

            else -> {}
        }
    }
    if (showBottomSheet) {
        LostConnectionBottomSheet(
            onTryAgainClick = {
                vm.getWasteDetails(wasteId)
                showBottomSheet = false
            },
            onDismissRequest = { showBottomSheet = false }
        )
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
                NormalText(
                    text = waste.description,
                    textAlign = TextAlign.Start
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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

@Composable
private fun SheetContentSkeleton(/*waste: Waste*/) {
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
                BoldedTextSkeleton(modifier = Modifier.fillMaxWidth(0.3f))
            }
            item {
                Column(verticalArrangement = spacedBy(2.dp)) {
                    NormalTextSkeleton(modifier = Modifier.fillMaxWidth(1f))
                    NormalTextSkeleton(modifier = Modifier.fillMaxWidth(0.5f))
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CategoryCardSkeleton()
                    CategoryCardSkeleton()
                    CategoryCardSkeleton()
                    CategoryCardSkeleton()

                }
            }
            item {
                HorizontalLine()
            }
            item {
                Column(verticalArrangement = spacedBy(2.dp)) {
                    SecondaryTextSkeleton(modifier = Modifier.fillMaxWidth(1f))
                    SecondaryTextSkeleton(modifier = Modifier.fillMaxWidth(0.5f))
                }
            }
        }
    }
}
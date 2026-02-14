package com.example.saferecycle.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.Waste
import com.example.saferecycle.ui.component.BoldedText
import com.example.saferecycle.ui.component.BoldedTextSkeleton
import com.example.saferecycle.ui.component.CategoryCard
import com.example.saferecycle.ui.component.CategoryCardSkeleton
import com.example.saferecycle.ui.component.InitialCard
import com.example.saferecycle.ui.component.InitialCardSkeleton
import com.example.saferecycle.ui.component.NormalText
import com.example.saferecycle.ui.component.NormalTextSkeleton
import com.example.saferecycle.ui.component.WasteCard
import com.example.saferecycle.ui.component.WasteCardSkeleton
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.ui.theme.fontFamily
import kotlin.collections.chunked
import kotlin.collections.forEach

@Composable
fun HeaderSection(
    username: String,
    initial: String,
    onInitialCardClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NameCatchPhrase(username = username)
        InitialCard(initial = initial, onClick = { onInitialCardClick() })
    }
}

@Composable
fun HeaderSectionSkeleton(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NameCatchPhraseSkeleton()
        InitialCardSkeleton()
    }
}

@Composable
fun NameCatchPhrase(username: String) {
    Column {
        NormalText(
            text = "Hello, $username",
            color = SafeRecycleTheme.colors.textSecondary
        )
        Text(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            text = buildAnnotatedString {
                append("What would you like\nto ")
                withStyle(style = SpanStyle(color = SafeRecycleTheme.colors.accent)) {
                    append("recycle")
                }
                append(" today?")
            }
        )
    }
}

@Composable
fun NameCatchPhraseSkeleton(modifier: Modifier = Modifier) {
    Column(verticalArrangement = spacedBy(5.dp)) {
        NormalTextSkeleton(modifier = Modifier.fillMaxWidth(0.3f))
        BoldedTextSkeleton(modifier = Modifier.fillMaxWidth(0.65f))
        BoldedTextSkeleton(modifier = Modifier.fillMaxWidth(0.5f))
    }
}

@Composable
fun ListTitle(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BoldedText(text = text)
        NormalText(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
            text = "See All",
            color = SafeRecycleTheme.colors.accent
        )
    }
}

@Composable
fun ListTileSkeleton(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BoldedTextSkeleton(modifier = Modifier.fillMaxWidth(0.4f))
        NormalTextSkeleton(modifier = Modifier.fillMaxWidth(0.2f))
    }
}

@Composable
fun CategorySection(
    onCategoriesClick: () -> Unit,
    onCategoryCardClick: (String, Int) -> Unit,
    categories: List<Category>
) {
    Column(verticalArrangement = spacedBy(16.dp)) {
        ListTitle(text = "Waste Category", onClick = onCategoriesClick)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = /*Arrangement.SpaceBetween*/ spacedBy(17.dp)
        ) {
            categories.take(4).forEach { category ->
                CategoryCard(
                    category = category,
                    onClick = {
                        onCategoryCardClick(
                            category.name,
                            category.id
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun CategorySectionSkeleton(
    modifier: Modifier = Modifier,
) {
    Column(verticalArrangement = spacedBy(16.dp)) {
        ListTileSkeleton()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = /*Arrangement.SpaceBetween*/ spacedBy(17.dp)
        ) {
            CategoryCardSkeleton()
            CategoryCardSkeleton()
            CategoryCardSkeleton()
            CategoryCardSkeleton()
        }
    }
}

@Composable
fun SuggestedSection(
    onSuggestedClick: () -> Unit,
    onWasteCardClick: (Int) -> Unit,
    suggestedWaste: List<Waste>
) {
    Column(verticalArrangement = spacedBy(16.dp)) {
        ListTitle(text = "Suggested for You", onClick = onSuggestedClick)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween /*spacedBy(19.dp)*/
        ) {
            suggestedWaste.take(2).forEach { waste ->
                WasteCard(
                    waste = waste,
                    onClick = { onWasteCardClick(waste.id) })
            }
        }
    }
}

@Composable
fun SuggestionSectionSkeleton(modifier: Modifier = Modifier) {
    Column(verticalArrangement = spacedBy(16.dp)) {
        ListTileSkeleton()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween /*spacedBy(19.dp)*/
        ) {
            WasteCardSkeleton()
            WasteCardSkeleton()
        }
    }
}

@Composable
fun PopularSection(
    onPopularClick: () -> Unit,
    onWasteCardClick: (Int) -> Unit,
    popularWaste: List<Waste>
) {
    Column(verticalArrangement = spacedBy(16.dp)) {
        ListTitle(text = "Popular Waste", onClick = onPopularClick)
        popularWaste.chunked(2).take(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween /*spacedBy(19.dp)*/
            ) {
                rowItems.forEach { waste ->
                    WasteCard(waste, onClick = { onWasteCardClick(waste.id) })
                }
            }
        }
    }
}

@Composable
fun PopularSectionSkeleton(modifier: Modifier = Modifier) {
    Column(verticalArrangement = spacedBy(16.dp)) {
        ListTileSkeleton()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween /*spacedBy(19.dp)*/
        ) {
            WasteCardSkeleton()
            WasteCardSkeleton()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween /*spacedBy(19.dp)*/
        ) {
            WasteCardSkeleton()
            WasteCardSkeleton()
        }
    }
}
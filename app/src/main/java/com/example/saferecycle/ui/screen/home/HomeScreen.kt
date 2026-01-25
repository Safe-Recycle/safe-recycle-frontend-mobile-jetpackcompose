package com.example.saferecycle.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.saferecycle.data.dummyCategories
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.ui.component.SearchField

@Composable
fun HomeScreen(onNavigateToCategory: () -> Unit) {
    Scaffold { innerPadding ->
        LazyColumn(
            verticalArrangement = spacedBy(24.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            item {
                HeaderSection(username = "Elma", initial = "E")
            }
            item {
                SearchField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(13.dp))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {}
                        .height(45.dp), value = ""
                )
            }
            item {
                CategorySection(
                    categories = dummyCategories,
                    onClickSeeAll = { onNavigateToCategory() },
                    onClickCategory = { },
                )
            }
            item {
                SuggestedSection(
                    suggestedWaste = dummyWastes,
                    onClickWaste = {},
                    onClickSeeAll = {}
                )
            }
            item {
                PopularSection(
                    popularWaste = dummyWastes,
                    onClickWaste = {},
                    onClickSeeAll = {}
                )
            }
        }
    }
}
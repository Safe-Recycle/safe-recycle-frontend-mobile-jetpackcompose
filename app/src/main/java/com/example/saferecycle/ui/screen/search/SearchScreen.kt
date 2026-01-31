package com.example.saferecycle.ui.screen.search

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.data.emptyWasteList
import com.example.saferecycle.ui.component.EmptyListIndicator
import com.example.saferecycle.ui.component.SearchField
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.component.WasteCard

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopBar(
            text = "Search",
            onBackClick = { onBackClick() }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = spacedBy(12.dp)
        ) {
            SearchField(
                modifier = Modifier
                    .clip(RoundedCornerShape(13.dp))
                    .height(45.dp),
                value = searchQuery,
                onValueChange = { searchQuery = it }
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                columns = GridCells.Adaptive(163.dp),
                horizontalArrangement = spacedBy(19.dp),
                verticalArrangement = spacedBy(15.dp)
            ) {
                items(dummyWastes) { waste ->
                    WasteCard(
                        waste = waste,
                        onClick = { onNavigateToDetailWaste(waste.id) }
                    )
                }
            }

            if (false/*emptyWasteList.isEmpty()*/) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    EmptyListIndicator(text = "Currently, there is no recorded waste\nmatching your search query. Try\nscanning the object")
                }
            }
        }
    }
}
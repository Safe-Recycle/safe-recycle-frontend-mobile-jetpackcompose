package com.example.saferecycle.ui.screen.waste_list

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.ui.component.EmptyListIndicator
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.component.WasteCard

@Composable
fun WasteListScreen(
    source: String,
    categoryId: Int? = null,
    onBackClick: () -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                text = source,
                onBackClick = { onBackClick() })
        }
    ) { innerPadding ->
        if (false/*emptyWasteList.isEmpty()*/) {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                EmptyListIndicator(text = "Currently, there is no recorded waste\ncategorized here yet")
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                columns = GridCells.Adaptive(163.dp),
                horizontalArrangement = spacedBy(19.dp),
                verticalArrangement = spacedBy(15.dp)
            ) {
                items(dummyWastes) { waste ->
                    WasteCard(
                        waste = waste,
                        onClick = { onNavigateToDetailWaste(waste.id) })
                }
            }
        }
    }
}
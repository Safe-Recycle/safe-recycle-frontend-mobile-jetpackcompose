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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saferecycle.data.dummyWastes
import com.example.saferecycle.data.network.Resource
import com.example.saferecycle.ui.component.EmptyListIndicator
import com.example.saferecycle.ui.component.TopBar
import com.example.saferecycle.ui.component.WasteCard
import com.example.saferecycle.ui.component.WasteCardSkeleton

@Composable
fun WasteListScreen(
    source: String,
    categoryId: Int? = null,
    onBackClick: () -> Unit,
    onNavigateToDetailWaste: (Int) -> Unit,
    vm: WasteListViewModel = hiltViewModel()
) {
    val wastesState by vm.wastes.collectAsState()
//    val categorizedWastes by vm.categorizedWastes.collectAsState()
//    val suggestedWastes by vm.suggestedWastes.collectAsState()
//    val popularWastes by vm.popularWastes.collectAsState()

    LaunchedEffect(Unit) {
        vm.getWastes()
    }
    Scaffold(
        topBar = {
            TopBar(
                text = source,
                onBackClick = { onBackClick() })
        }
    ) { innerPadding ->
        when (wastesState) {
            is Resource.Empty<*> -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    EmptyListIndicator(text = "Currently, there is no recorded waste\ncategorized here yet")
                }
            }

            is Resource.Loading<*> -> {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp),
                    columns = GridCells.Adaptive(163.dp),
                    horizontalArrangement = spacedBy(19.dp),
                    verticalArrangement = spacedBy(15.dp)
                ) {
                    items(12) { waste ->
                        WasteCardSkeleton()
                    }
                }
            }

            is Resource.Success<*> -> {
                val wastes = (wastesState as Resource.Success).data
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp),
                    columns = GridCells.Adaptive(163.dp),
                    horizontalArrangement = spacedBy(19.dp),
                    verticalArrangement = spacedBy(15.dp)
                ) {
                    items(wastes) { waste ->
                        WasteCard(
                            waste = waste,
                            onClick = { onNavigateToDetailWaste(waste.id) })
                    }
                }
            }

            else -> {}
        }
    }
}
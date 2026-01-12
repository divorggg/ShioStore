package com.example.shiostore.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shiostore.domain.model.Product
import com.example.shiostore.domain.model.dummyProducts
import com.example.shiostore.ui.components.EmptyState
import com.example.shiostore.ui.components.ProductSkeleton
import com.example.shiostore.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNav() }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                SearchBar(
                    query = uiState.query,
                    onQueryChange = viewModel::onQueryChange
                )
            }

            item { FeaturedSection() }

            item { PromoBanner() }

            item { DealOfDay() }

            item {
                when {
                    uiState.isLoading -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.height(300.dp),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(4) {
                                ProductSkeleton()
                            }
                        }
                    }

                    uiState.filteredProducts.isEmpty() -> {
                        EmptyState("Produk tidak ditemukan")
                    }

                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.height(520.dp),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(uiState.filteredProducts) { product ->
                                ProductCard(
                                    product = product,
                                    onClick = {
                                        navController.navigate(
                                            Screen.Detail.createRoute(product.id)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

//            item { SpecialOfferBanner() }
//
//            item { TrendingSection() }

            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}



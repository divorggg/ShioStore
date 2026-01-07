package com.example.shiostore.ui.home

import com.example.shiostore.domain.model.Product

data class HomeUiState(
    val query: String = "",
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val isLoading: Boolean = true

)

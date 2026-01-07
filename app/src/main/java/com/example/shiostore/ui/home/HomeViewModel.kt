package com.example.shiostore.ui.home

import androidx.lifecycle.ViewModel
import com.example.shiostore.domain.model.dummyProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            products = dummyProducts(),
            filteredProducts = dummyProducts()
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onQueryChange(query: String) {
        val allProducts = _uiState.value.products

        val filtered = if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }

        _uiState.value = _uiState.value.copy(
            query = query,
            filteredProducts = filtered
        )
    }
}

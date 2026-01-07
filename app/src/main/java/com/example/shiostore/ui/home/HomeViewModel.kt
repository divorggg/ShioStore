package com.example.shiostore.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiostore.domain.model.dummyProducts
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            delay(1000) // simulasi loading
            val products = dummyProducts()
            _uiState.value = HomeUiState(
                products = products,
                filteredProducts = products,
                isLoading = false
            )
        }
    }

    fun onQueryChange(query: String) {
        val allProducts = _uiState.value.products
        val filtered = if (query.isBlank()) allProducts else {
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


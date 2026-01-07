package com.example.shiostore.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiostore.data.repository.ProductRepositoryImpl
import com.example.shiostore.data.source.ProductDataSource
import com.example.shiostore.domain.model.dummyProducts
import com.example.shiostore.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val getProductsUseCase = GetProductsUseCase(
        ProductRepositoryImpl(ProductDataSource())
    )

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val products = getProductsUseCase()
            _uiState.value = HomeUiState(
                products = products,
                filteredProducts = products,
                isLoading = false
            )
        }
    }

    fun onQueryChange(query: String) {
        val filtered = _uiState.value.products.filter {
            it.name.contains(query, ignoreCase = true)
        }

        _uiState.value = _uiState.value.copy(
            query = query,
            filteredProducts = filtered
        )
    }
}



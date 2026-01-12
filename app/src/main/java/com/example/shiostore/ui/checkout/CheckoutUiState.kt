package com.example.shiostore.ui.checkout

data class CheckoutUiState(
    val address: String = "",
    val paymentMethod: String = "Transfer Bank",
    val isLoading: Boolean = false
)

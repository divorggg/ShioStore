package com.example.shiostore.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{productId}") {
        fun createRoute(productId: Int) = "detail/$productId"
    }
    object Checkout : Screen("checkout/{productId}") {
        fun createRoute(productId: Int) = "checkout/$productId"
    }

    object PaymentSuccess : Screen("payment_success")
}
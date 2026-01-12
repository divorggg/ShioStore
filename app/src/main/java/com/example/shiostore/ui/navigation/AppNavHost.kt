package com.example.shiostore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shiostore.ui.checkout.CheckoutScreen
import com.example.shiostore.ui.detail.DetailScreen
import com.example.shiostore.ui.home.HomeScreen
import com.example.shiostore.ui.order.CartScreen
import com.example.shiostore.ui.payment.PaymentSuccessScreen
import com.example.shiostore.ui.profile.ProfileScreen
import com.example.shiostore.ui.navigation.Screen



@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Cart.route) {
            CartScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            DetailScreen(
                navController = navController,
                productId = productId
            )
        }

        composable(
            route = Screen.Checkout.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) {
            val productId = it.arguments?.getInt("productId") ?: -1
            CheckoutScreen(navController, productId)
        }


        composable(Screen.PaymentSuccess.route) {
            PaymentSuccessScreen(navController)
        }

    }
}


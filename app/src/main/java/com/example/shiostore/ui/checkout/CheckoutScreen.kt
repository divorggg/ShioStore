package com.example.shiostore.ui.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shiostore.domain.model.dummyProducts
import com.example.shiostore.ui.navigation.Screen
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    productId: Int,
    viewModel: CheckoutViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val product = dummyProducts().firstOrNull { it.id == productId }

    if (product == null) {
        Text("Produk tidak ditemukan")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        },
        bottomBar = {
            CheckoutBottomBar(
                totalPrice = product.price,
                isLoading = uiState.isLoading,
                enabled = uiState.address.isNotBlank(),
                onPayClick = {
                    viewModel.pay {
                        navController.navigate(Screen.PaymentSuccess.route) {
                            popUpTo(Screen.Home.route)
                        }
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            ProductSummaryCard(product)

            Spacer(Modifier.height(20.dp))

            AddressSection(
                address = uiState.address,
                onAddressChange = viewModel::onAddressChange
            )

            Spacer(Modifier.height(20.dp))

            PaymentMethodSection(
                selected = uiState.paymentMethod,
                onSelect = viewModel::onPaymentChange
            )
        }
    }
}



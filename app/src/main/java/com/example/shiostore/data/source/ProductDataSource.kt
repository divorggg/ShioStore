package com.example.shiostore.data.source

import com.example.shiostore.domain.model.Product
import com.example.shiostore.domain.model.dummyProducts
import kotlinx.coroutines.delay

class ProductDataSource {

    suspend fun getProducts(): List<Product> {
        delay(1000) // simulasi API
        return dummyProducts()
    }
}

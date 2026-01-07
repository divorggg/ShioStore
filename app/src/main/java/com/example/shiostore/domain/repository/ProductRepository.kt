package com.example.shiostore.domain.repository

import com.example.shiostore.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}

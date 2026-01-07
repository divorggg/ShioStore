package com.example.shiostore.domain.usecase

import com.example.shiostore.domain.model.Product
import com.example.shiostore.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return repository.getProducts()
    }
}

package com.example.shiostore.data.repository

import com.example.shiostore.data.source.ProductDataSource
import com.example.shiostore.domain.model.Product
import com.example.shiostore.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val dataSource: ProductDataSource
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return dataSource.getProducts()
    }
}

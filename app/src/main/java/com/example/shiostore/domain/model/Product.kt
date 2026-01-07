package com.example.shiostore.domain.model

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String
)
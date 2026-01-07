package com.example.shiostore.domain.model

fun dummyProducts() = listOf(
    Product(
        id = 1,
        name = "Sepatu Sneakers",
        price = 250000,
        image = "https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77",
        description = "Sepatu sneakers nyaman digunakan untuk aktivitas sehari-hari."
    ),
    Product(
        id = 2,
        name = "Tas Wanita",
        price = 180000,
        image = "https://images.unsplash.com/photo-1585386959984-a4155228b36b",
        description = "Tas wanita dengan desain elegan dan bahan berkualitas."
    ),
    Product(
        id = 3,
        name = "Jam Tangan",
        price = 320000,
        image = "https://images.unsplash.com/photo-1523275335684-37898b6baf30",
        description = "Jam tangan modern dengan tampilan stylish dan presisi tinggi."
    ),
    Product(
        id = 4,
        name = "Headphone",
        price = 450000,
        image = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e",
        description = "Headphone dengan kualitas suara jernih dan bass yang kuat."
    )
)

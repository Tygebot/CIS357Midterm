package com.example.gvsufoodmap.model


data class FoodLocation(
    val id: String,
    val name: String,
// normalized coordinates on the fake map image [0f..1f]
    val x: Float,
    val y: Float,
    val category: Category,
    val open24h: Boolean = false
)


enum class Category { RESTAURANT, STORE, VENDING, CAFE, OTHER }
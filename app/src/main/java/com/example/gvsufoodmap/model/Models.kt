package com.example.gvsufoodmap.model

data class FoodLocation(
    val id: String,
    val name: String,
    // Real-world coordinates for Google Maps
    val lat: Double,
    val lng: Double,
    val category: Category,
    val open24h: Boolean = false,
    // Simple description of what this place has
    val items: List<String> = emptyList()
)

enum class Category {
    RESTAURANT,
    STORE,
    VENDING,
    CAFE,
    OTHER
}

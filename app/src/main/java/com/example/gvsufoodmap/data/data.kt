package com.example.gvsufoodmap.data

import com.example.gvsufoodmap.model.Category
import com.example.gvsufoodmap.model.FoodLocation

object FakeRepo {
    // Simple hard-coded sample locations on the Allendale campus.
    // Co-ordinates are approximate, just enough to get pins in the right area.
    val locations = listOf(
        FoodLocation(
            id = "commons",
            name = "Fresh Food Co. (Commons)",
            lat = 42.9635,
            lng = -85.8887,
            category = Category.RESTAURANT,
            items = listOf(
                "All-you-care-to-eat meals",
                "Grill & pizza",
                "Salad bar",
                "Desserts"
            )
        ),
        FoodLocation(
            id = "kirkhof-panda",
            name = "Panda Express – Kirkhof Center",
            lat = 42.9639,
            lng = -85.8892,
            category = Category.RESTAURANT,
            items = listOf(
                "Orange Chicken",
                "Fried rice & chow mein",
                "Bowl & plate combos"
            )
        ),
        FoodLocation(
            id = "kirkhof-pod",
            name = "Lobby Shop POD – Kirkhof",
            lat = 42.9638,
            lng = -85.8891,
            category = Category.STORE,
            items = listOf(
                "Snacks & candy",
                "Bottled drinks",
                "Grab-and-go sandwiches",
                "Microwave meals"
            )
        ),
        FoodLocation(
            id = "library-vending",
            name = "Mary Idema Pew Library Vending",
            lat = 42.9629,
            lng = -85.8895,
            category = Category.VENDING,
            open24h = true,
            items = listOf(
                "Cold drinks",
                "Chips",
                "Candy bars",
                "Late-night study snacks"
            )
        ),
        FoodLocation(
            id = "au-bon-pain",
            name = "Au Bon Pain – Library",
            lat = 42.9631,
            lng = -85.8894,
            category = Category.CAFE,
            items = listOf(
                "Coffee & espresso",
                "Soup",
                "Sandwiches & wraps",
                "Bakery items"
            )
        )
    )
}

package com.example.gvsufoodmap.data


import com.example.gvsufoodmap.model.Category
import com.example.gvsufoodmap.model.FoodLocation


object FakeRepo {
    val locations = listOf(
        FoodLocation("commons", "The Commons Dining", 0.42f, 0.55f, Category.RESTAURANT),
        FoodLocation("kirkhof-panda", "Panda Express (Kirkhof)", 0.48f, 0.52f, Category.RESTAURANT),
        FoodLocation("kirkhof-cstore", "Lobby Shop (Kirkhof)", 0.49f, 0.50f, Category.STORE),
        FoodLocation("library-vending", "Mary Idema Pew Library Vending", 0.60f, 0.40f, Category.VENDING, open24h = true),
        FoodLocation("au-bon-pain", "Au Bon Pain", 0.35f, 0.62f, Category.CAFE)
    )
}
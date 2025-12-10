package com.example.gvsufoodmap.data

import com.example.gvsufoodmap.model.Category
import com.example.gvsufoodmap.model.FoodLocation

object FakeRepo {

    val locations = listOf(
        // ===== Your originals =====
        FoodLocation(
            id = "commons",
            name = "Fresh Food Co. (Commons)",
            lat = 42.9656633924634,
            lng = -85.886692304709,
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
            lat = 42.962844817500745,
            lng = -85.88907279007397,
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
            lat = 42.96255762259384,
            lng = -85.88877948845,
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
            lat = 42.96305708844293,
            lng = -85.8896452975248,
            category = Category.VENDING,
            open24h = true,
            items = listOf(
                "Cold drinks",
                "Chips",
                "Candy bars",
                "Late-night study snacks"
            )
        ),

        // ===== Allendale – Kleiner Commons =====
        FoodLocation(
            id = "kleiner-dish",
            name = "The Dish – Kleiner Commons",
            lat = 42.96892832739227,
            lng = -85.88550162209259,
            category = Category.RESTAURANT,
            items = listOf(
                "All-you-care-to-eat meals",
                "Homestyle entrées",
                "Vegan & vegetarian options"
            )
        ),
        FoodLocation(
            id = "kleiner-late-night",
            name = "Late Night – Kleiner Commons",
            lat = 42.96946304588327,
            lng = -85.88547004565493,
            category = Category.RESTAURANT,
            items = listOf(
                "Appetizers",
                "Wings & fries",
                "Desserts"
            )
        ),
        FoodLocation(
            id = "kleiner-pod-java",
            name = "POD / Java City – Kleiner",
            lat = 42.96875338761751,
            lng = -85.88593918130022,
            category = Category.STORE,
            items = listOf(
                "Convenience groceries",
                "Bottled drinks",
                "Coffee & espresso",
                "Grab-and-go snacks"
            )
        ),

        // ===== Allendale – Mackinac =====
        FoodLocation(
            id = "mackinac-pod",
            name = "POD Express – Mackinac",
            lat = 42.966850128505534,
            lng = -85.88712120031272,
            category = Category.STORE,
            items = listOf(
                "Snacks & candy",
                "Coffee & hot drinks",
                "Grab-and-go items"
            )
        ),

        // ===== Allendale – Holton-Hooker =====
        FoodLocation(
            id = "holton-einstein",
            name = "Einstein Bros – Holton-Hooker",
            lat = 42.966549193997494,
            lng = -85.88586418182767,
            category = Category.CAFE,
            items = listOf(
                "Bagels & shmear",
                "Breakfast sandwiches",
                "Coffee & espresso"
            )
        ),


        // ===== Allendale – Marketplace =====
        FoodLocation(
            id = "marketplace-starbucks",
            name = "Starbucks – Marketplace",
            lat = 42.96541115978727,
            lng = -85.8895390289354,
            category = Category.CAFE,
            items = listOf(
                "Espresso drinks",
                "Cold brew & refreshers",
                "Pastries & breakfast items"
            )
        ),
        FoodLocation(
            id = "marketplace-bento",
            name = "Bento – Marketplace",
            lat = 42.96547184670287,
            lng = -85.88936040563313,
            category = Category.RESTAURANT,
            items = listOf(
                "Ramen bowls",
                "Poke bowls",
                "Sushi rolls"
            )
        ),

        // ===== Allendale – Library (GV Brew in addition to your vending/Au Bon) =====
        FoodLocation(
            id = "library-gv-brew",
            name = "GV Brew – Library",
            lat = 42.962898637319455,
            lng =-85.88969604537411,
            category = Category.CAFE,
            items = listOf(
                "Coffee & espresso",
                "Boba drinks",
                "Bakery treats"
            )
        ),
        FoodLocation(
            id = "kirkhof-qdoba",
            name = "Qdoba – Kirkhof",
            lat = 42.962838545313915,
            lng = -85.88880986648391,
            category = Category.RESTAURANT,
            items = listOf(
                "Burritos & bowls",
                "Tacos",
                "Chips & queso"
            )
        ),
        FoodLocation(
            id = "kirkhof-erberts",
            name = "Erbert & Gerbert’s – Kirkhof",
            lat = 42.96295435214239,
            lng = -85.88831097564345,
            category = Category.RESTAURANT,
            items = listOf(
                "Cold & toasted subs",
                "Soup",
                "Chips & cookies"
            )
        ),
        FoodLocation(
            id = "kirkhof-ciao",
            name = "Ciao – Kirkhof",
            lat = 42.96282087984412,
            lng = -85.88849336584737,
            category = Category.RESTAURANT,
            items = listOf(
                "Pizza & breadsticks",
                "Pasta bowls",
                "Sides & salads"
            )
        ),
        FoodLocation(
            id = "kirkhof-laker-grill",
            name = "Laker Grill – Kirkhof",
            lat = 42.962599079644036,
            lng = -85.8883458443759,
            category = Category.RESTAURANT,
            items = listOf(
                "Burgers & chicken sandwiches",
                "Fries & onion rings",
                "Shakes"
            )
        ),

        // ===== Allendale – The Connection =====
        FoodLocation(
            id = "connection-pod-java",
            name = "POD / Java City – Connection",
            lat = 42.95968137140101,
            lng = -85.88857819291478,
            category = Category.STORE,
            items = listOf(
                "Convenience groceries",
                "Bottled drinks",
                "Coffee & espresso"
            )
        ),
        FoodLocation(
            id = "connection-zoca",
            name = "Zoca – Connection",
            lat = 42.959707049031316,
            lng = -85.88835650863453,
            category = Category.RESTAURANT,
            items = listOf(
                "Burritos & tacos",
                "Bowls & nachos",
                "Fresh salsas"
            )
        ),
        FoodLocation(
            id = "connection-late-night",
            name = "Late Night – Connection",
            lat = 42.9597595714243,
            lng = -85.88828155064404,
            category = Category.RESTAURANT,
            items = listOf(
                "Late-night appetizers",
                "Fried snacks",
                "Desserts"
            )
        ),

        // ===== Allendale – Concessions & partners =====
        FoodLocation(
            id = "lubbers-concessions",
            name = "Lubbers Stadium Concessions",
            lat = 42.96816960666398,
            lng = -85.89489468091953,
            category = Category.VENDING,
            items = listOf(
                "Game-day snacks",
                "Hot dogs & nachos",
                "Soft drinks"
            )
        ),

        // ===== Pew Campus (Downtown Grand Rapids) =====
        FoodLocation(
            id = "idc-starbucks",
            name = "Starbucks – Innovation Design Center",
            lat = 42.9659,
            lng = -85.6733,
            category = Category.CAFE,
            items = listOf(
                "Espresso drinks",
                "Cold brew & refreshers",
                "Pastries"
            )
        ),
        FoodLocation(
            id = "devos-einstein",
            name = "Einstein Bros – DeVos Center",
            lat = 42.9650,
            lng = -85.6738,
            category = Category.CAFE,
            items = listOf(
                "Bagels & shmear",
                "Breakfast & lunch sandwiches",
                "Coffee"
            )
        ),
        FoodLocation(
            id = "devos-erberts",
            name = "Erbert & Gerbert’s – DeVos Center",
            lat = 42.9650,
            lng = -85.6738,
            category = Category.RESTAURANT,
            items = listOf(
                "Subs",
                "Soup",
                "Chips & cookies"
            )
        ),
        FoodLocation(
            id = "devos-laker-store",
            name = "Laker Store Grab ’n Go – DeVos",
            lat = 42.9650,
            lng = -85.6738,
            category = Category.STORE,
            items = listOf(
                "Grab-and-go snacks",
                "Bottled drinks",
                "Microwaveable meals"
            )
        ),
        FoodLocation(
            id = "seidman-cafe",
            name = "Seidman Café – Seidman Center",
            lat = 42.9641,
            lng = -85.6717,
            category = Category.CAFE,
            items = listOf(
                "Salads & wraps",
                "Quesadillas",
                "Snacks & drinks"
            )
        )
    )
}

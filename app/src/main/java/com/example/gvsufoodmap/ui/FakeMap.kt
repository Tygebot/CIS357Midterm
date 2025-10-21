package com.example.gvsufoodmap.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gvsufoodmap.R
import com.example.gvsufoodmap.data.FakeRepo
import com.example.gvsufoodmap.model.Category
import com.example.gvsufoodmap.state.AppState

@Composable
fun MapScreen() {
    val markerColor = AppState.markerColor.value
    val filters = AppState.filters.value

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.campus_map_placeholder),
            contentDescription = "Campus Map",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val w = maxWidth.value   // Dp -> Float
            val h = maxHeight.value

            FakeRepo.locations
                .filter { loc ->
                    when (loc.category) {
                        Category.RESTAURANT -> filters.restaurant
                        Category.STORE      -> filters.store
                        Category.VENDING    -> filters.vending
                        Category.CAFE       -> filters.cafe
                        Category.OTHER      -> filters.other
                    } && (!filters.only24h || loc.open24h)
                }
                .forEach { loc ->
                    val x = (w * loc.x).dp  // Float -> Dp
                    val y = (h * loc.y).dp
                    MarkerDot(x, y, Color(markerColor), loc.name)
                }
        }
    }
}

@Composable
private fun MarkerDot(x: Dp, y: Dp, color: Color, label: String) {
    val show = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .absoluteOffset(x = x, y = y)
                .clip(CircleShape)
                .background(color)
                .clickable { show.value = !show.value }
                .fillMaxSize(0f)
        )
        if (show.value) {
            Surface(
                tonalElevation = 2.dp,
                shadowElevation = 2.dp,
                modifier = Modifier.absoluteOffset(x = x + 12.dp, y = y - 8.dp)
            ) {
                Text(label, modifier = Modifier.background(Color.Transparent),
                    style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

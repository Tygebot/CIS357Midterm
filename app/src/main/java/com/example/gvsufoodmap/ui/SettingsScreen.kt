package com.example.gvsufoodmap.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gvsufoodmap.state.AppState


@Composable
fun SettingsScreen() {
    val filters = AppState.filters.value
    var r by remember { mutableStateOf((AppState.markerColor.value shr 16) and 0xFF) }
    var g by remember { mutableStateOf((AppState.markerColor.value shr 8) and 0xFF) }
    var b by remember { mutableStateOf(AppState.markerColor.value and 0xFF) }


    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Marker Color", style = MaterialTheme.typography.titleMedium)
        ColorSlider("R", r) { r = it; AppState.markerColor.value = argb(r, g, b) }
        ColorSlider("G", g) { g = it; AppState.markerColor.value = argb(r, g, b) }
        ColorSlider("B", b) { b = it; AppState.markerColor.value = argb(r, g, b) }


        Divider()
        Text("Filters", style = MaterialTheme.typography.titleMedium)
        LabeledSwitch("Restaurants", filters.restaurant) { AppState.filters.value = filters.copy(restaurant = it) }
        LabeledSwitch("Stores", filters.store) { AppState.filters.value = filters.copy(store = it) }
        LabeledSwitch("Vending", filters.vending) { AppState.filters.value = filters.copy(vending = it) }
        LabeledSwitch("Cafés", filters.cafe) { AppState.filters.value = filters.copy(cafe = it) }
        LabeledSwitch("Other", filters.other) { AppState.filters.value = filters.copy(other = it) }
        LabeledSwitch("Only 24h", filters.only24h) { AppState.filters.value = filters.copy(only24h = it) }
    }
}


@Composable private fun ColorSlider(label: String, value: Int, onChange: (Int) -> Unit) {
    Column { Text("$label: $value"); Slider(value = value.toFloat(), onValueChange = { onChange(it.toInt()) }, valueRange = 0f..255f) }
}


private fun argb(r: Int, g: Int, b: Int): Int = (0xFF shl 24) or (r shl 16) or (g shl 8) or b


@Composable private fun LabeledSwitch(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
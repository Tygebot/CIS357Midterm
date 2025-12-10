package com.example.gvsufoodmap.ui

import android.graphics.Color as AndroidColor
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gvsufoodmap.data.FakeRepo
import com.example.gvsufoodmap.model.Category
import com.example.gvsufoodmap.model.FoodLocation
import com.example.gvsufoodmap.state.AppState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapScreen() {
    val markerColorInt = AppState.markerColor.value
    val filters = AppState.filters.value
    val notes by AppState.notes   // user reviews / star ratings

    val context = LocalContext.current

    // MapView lives across recompositions
    val mapView = rememberMapViewWithLifecycle(context)

    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }
    var selectedLocationId by remember { mutableStateOf<String?>(null) }

    // Filter locations based on switches in Settings
    val filteredLocations by remember(filters) {
        mutableStateOf(
            FakeRepo.locations.filter { loc ->
                val allowed = when (loc.category) {
                    Category.RESTAURANT -> filters.restaurant
                    Category.STORE      -> filters.store
                    Category.VENDING    -> filters.vending
                    Category.CAFE       -> filters.cafe
                    Category.OTHER      -> filters.other
                }
                allowed && (!filters.only24h || loc.open24h)
            }
        )
    }

    val markerIcon = remember(markerColorInt) {
        markerIconFromArgb(markerColorInt)
    }

    // When we get a GoogleMap instance or data changes, update markers
    LaunchedEffect(googleMap, filteredLocations, markerColorInt, notes) {
        val map = googleMap ?: return@LaunchedEffect
        map.clear()

        val campusCenter = LatLng(42.963936, -85.888946)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(campusCenter, 16f))

        filteredLocations.forEach { loc ->
            val position = LatLng(loc.lat, loc.lng)
            val locReviews = notes.filter { it.locationId == loc.id }
            val avgRating = locReviews.map { it.rating }.average().takeIf { !it.isNaN() }

            val marker = map.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(loc.name)
                    .snippet(
                        avgRating?.let {
                            "Rating: %.1f/5 (%d reviews)".format(it, locReviews.size)
                        } ?: "No reviews yet"
                    )
                    .icon(markerIcon)
            )
            marker?.tag = loc.id
        }

        map.setOnMarkerClickListener { marker ->
            val id = marker.tag as? String
            selectedLocationId = id
            false // also show default info window
        }
    }

    val selectedLocation = remember(selectedLocationId, filteredLocations) {
        filteredLocations.firstOrNull { it.id == selectedLocationId } ?:
        FakeRepo.locations.firstOrNull { it.id == selectedLocationId }
    }

    Box(Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                // Initialize Google Maps
                MapsInitializer.initialize(context)
                mapView.apply {
                    getMapAsync { map ->
                        googleMap = map
                        map.uiSettings.isZoomControlsEnabled = true
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (selectedLocation != null) {
            val locationReviews = notes.filter { it.locationId == selectedLocation.id }
            LocationDetailsPanel(
                location = selectedLocation,
                reviews = locationReviews,
                onAddReview = { title, body, rating ->
                    AppState.addNote(selectedLocation.id, title, body, rating)
                },
                onDismiss = { selectedLocationId = null },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun rememberMapViewWithLifecycle(context: Context): MapView {
    val mapView = remember {
        MapView(context).apply {
            // You can pass a Bundle here if you handle saved state
            onCreate(null)
        }
    }

    // Hook MapView into Compose lifecycle
    DisposableEffect(Unit) {
        mapView.onStart()
        mapView.onResume()

        onDispose {
            mapView.onPause()
            mapView.onStop()
            mapView.onDestroy()
        }
    }

    return mapView
}

// Use the RGB sliders from Settings to tint the marker on the real map
private fun markerIconFromArgb(argb: Int): BitmapDescriptor {
    val r = (argb shr 16) and 0xFF
    val g = (argb shr 8) and 0xFF
    val b = argb and 0xFF
    val hsv = FloatArray(3)
    AndroidColor.RGBToHSV(r, g, b, hsv)
    return BitmapDescriptorFactory.defaultMarker(hsv[0])
}

@Composable
private fun LocationDetailsPanel(
    location: FoodLocation,
    reviews: List<AppState.Note>,
    onAddReview: (String, String, Int) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(3f) }

    val avgRating = reviews.map { it.rating }.average().takeIf { !it.isNaN() }

    Surface(
        modifier = modifier,
        tonalElevation = 6.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(location.name, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = when (location.category) {
                            Category.RESTAURANT -> "Dining hall / restaurant"
                            Category.STORE      -> "Store / POD"
                            Category.VENDING    -> "Vending"
                            Category.CAFE       -> "Café"
                            Category.OTHER      -> "Other"
                        },
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                }
            }

            if (location.items.isNotEmpty()) {
                Text("What they have:", style = MaterialTheme.typography.labelMedium)
                location.items.forEach { item ->
                    Text("• $item", style = MaterialTheme.typography.bodyMedium)
                }
            }

            if (avgRating != null) {
                Text(
                    "Average rating: %.1f/5 (%d reviews)".format(avgRating, reviews.size),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text("No reviews yet – be the first!", style = MaterialTheme.typography.bodyMedium)
            }

            if (reviews.isNotEmpty()) {
                Divider()
                Text("Recent reviews:", style = MaterialTheme.typography.labelMedium)
                reviews.take(3).forEach { note ->
                    ElevatedCard(Modifier.fillMaxWidth()) {
                        Column(
                            Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                note.title.ifBlank { "(No name)" },
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(note.body, style = MaterialTheme.typography.bodySmall)
                            Text(
                                "Rating: ${note.rating}/5",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }

            Divider()

            Text("Leave a review", style = MaterialTheme.typography.titleSmall)
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Name (optional)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("What did you think?") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Rating: ${rating.toInt()}/5")
                Slider(
                    value = rating,
                    onValueChange = { rating = it },
                    valueRange = 1f..5f,
                    steps = 3
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        val finalRating = rating.toInt().coerceIn(1, 5)
                        onAddReview(title, body, finalRating)
                        title = ""
                        body = ""
                        rating = 3f
                    }
                ) {
                    Text("Save review")
                }
            }
        }
    }
}

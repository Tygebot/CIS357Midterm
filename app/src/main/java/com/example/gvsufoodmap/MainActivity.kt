package com.example.gvsufoodmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gvsufoodmap.ui.MapScreen
import com.example.gvsufoodmap.ui.NotesScreen
import com.example.gvsufoodmap.ui.SettingsScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
                val navController = rememberNavController()
                val backStack by navController.currentBackStackEntryAsState()
                val currentRoute = backStack?.destination?.route ?: Routes.Map

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("GVSU Food") }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding) // <-- consume Scaffold padding
                    ) {
                        TabRowWithRoutes(currentRoute) { route ->
                            if (route != currentRoute) navController.navigate(route)
                        }
                        NavHost(
                            navController = navController,
                            startDestination = Routes.Map,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable(Routes.Map) { MapScreen() }
                            composable(Routes.Settings) { SettingsScreen() }
                            composable(Routes.Notes) { NotesScreen() }
                        }
                    }
                }
            }
        }
    }
}

object Routes {
    const val Map = "map"
    const val Settings = "settings"
    const val Notes = "notes"
}

@Composable
fun TabRowWithRoutes(currentRoute: String, onSelect: (String) -> Unit) {
    val tabs = listOf(Routes.Map, Routes.Settings, Routes.Notes)
    TabRow(selectedTabIndex = tabs.indexOf(currentRoute).coerceAtLeast(0)) {
        tabs.forEach { route ->
            val label = when (route) {
                Routes.Map -> "Map"
                Routes.Settings -> "Settings"
                Routes.Notes -> "Notes"
                else -> route
            }
            Tab(
                selected = route == currentRoute,
                onClick = { onSelect(route) },
                text = { Text(label) }
            )
        }
    }
}

package com.example.gvsufoodmap.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gvsufoodmap.data.FakeRepo
import com.example.gvsufoodmap.state.AppState


@Composable
fun NotesScreen() {
    val notes by remember { AppState.notes }
    var selectedLocationId by remember { mutableStateOf(FakeRepo.locations.first().id) }
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(3f) }


    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
// Location picker
        var expanded by remember { mutableStateOf(false) }
        OutlinedButton(onClick = { expanded = true }) { Text(FakeRepo.locations.firstOrNull { it.id == selectedLocationId }?.name ?: "Select location") }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            FakeRepo.locations.forEach { loc ->
                DropdownMenuItem(text = { Text(loc.name) }, onClick = { selectedLocationId = loc.id; expanded = false })
            }
        }


        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = body, onValueChange = { body = it }, label = { Text("Body") }, modifier = Modifier.fillMaxWidth())
        Text("Rating: ${'$'}{rating.toInt()}")
        Slider(value = rating, onValueChange = { rating = it }, valueRange = 0f..5f)


        Button(onClick = {
            if (title.isNotBlank() || body.isNotBlank()) {
                AppState.addNote(selectedLocationId, title, body, rating.toInt())
                title = ""; body = ""; rating = 3f
            }
        }) { Text("Add Note") }


        Divider()
        Text("All Notes", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(notes) { n ->
                ElevatedCard(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(n.title.ifBlank { "(No title)" }, style = MaterialTheme.typography.titleSmall)
                        Text(n.body)
                        Text("Rating: ${'$'}{n.rating}/5", style = MaterialTheme.typography.labelMedium)
                        Row { Text("For: ${'$'}{n.locationId}", style = MaterialTheme.typography.labelSmall); Spacer(Modifier.weight(1f)); TextButton(onClick = { AppState.deleteNote(n.id) }) { Text("Delete") } }
                    }
                }
            }
        }
    }
}
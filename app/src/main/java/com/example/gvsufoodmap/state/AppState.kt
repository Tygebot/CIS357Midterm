package com.example.gvsufoodmap.state


import androidx.compose.runtime.mutableStateOf
import com.example.gvsufoodmap.model.Category


object AppState {
    // Marker color (ARGB)
    val markerColor = mutableStateOf(0xFF00796B.toInt())


    data class Filters(
        val restaurant: Boolean = true,
        val store: Boolean = true,
        val vending: Boolean = true,
        val cafe: Boolean = true,
        val other: Boolean = true,
        val only24h: Boolean = false
    )


    val filters = mutableStateOf(Filters())


    // Simple in-memory notes per location
    data class Note(
        val id: Long,
        val locationId: String,
        val title: String,
        val body: String,
        val rating: Int
    )


    private var noteId = 0L
    val notes = mutableStateOf(listOf<Note>())


    fun addNote(locationId: String, title: String, body: String, rating: Int) {
        noteId += 1
        notes.value = notes.value + Note(noteId, locationId, title, body, rating)
    }


    fun deleteNote(id: Long) { notes.value = notes.value.filterNot { it.id == id } }
}
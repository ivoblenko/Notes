package com.example.notes.list.model

import com.example.notes.model.Note

data class ListState(
    val notes: MutableList<Note> = mutableListOf()
)
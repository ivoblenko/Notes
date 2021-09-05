package com.example.notes.editor.model

import com.example.notes.model.Note

data class EditorState(
    val note: Note = Note(-1, "", "")
)
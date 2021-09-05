package com.example.notes.list.model

import com.example.notes.model.Note

sealed class ListAction {
    data class NavigateToEditor(
        val index: Long,
        val note: Note
    ) : ListAction()

    data class NavigateToNewEditor(
        val note: Note = Note(-1, "", "")
    ) : ListAction()
}
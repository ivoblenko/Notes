package com.example.notes.list.model

import com.example.notes.model.Note

sealed class ListAction {
    data class NavigateToEditor(
        val note: Note
    ) : ListAction()

    data class NavigateToNewEditor(
        val note: Note = Note()
    ) : ListAction()

    object DeleteNote : ListAction()
}
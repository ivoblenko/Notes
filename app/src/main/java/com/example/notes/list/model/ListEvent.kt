package com.example.notes.list.model

import com.example.notes.model.Note

sealed class ListEvent {
    data class NoteClick(
        val note: Note
    ) : ListEvent()

    object NewNote : ListEvent()

    data class DeleteNote(
        val note: Note
    ) : ListEvent()

    object OnListCreate : ListEvent()
}
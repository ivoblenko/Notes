package com.example.notes.editor.model

import com.example.notes.model.Note

sealed class EditorEvent {

    data class UpdateNote(
        val newTitle: String,
        val newText: String
    ) : EditorEvent()

    data class SaveNote(
        val note: Note
    ) : EditorEvent()

    object NavigateToNoteList : EditorEvent()

//    data class OnViewCreated(
//        val index: Int,
//        val note: Note
//    )
}
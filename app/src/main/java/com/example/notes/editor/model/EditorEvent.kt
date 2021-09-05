package com.example.notes.editor.model

import com.example.notes.model.Note

sealed class EditorEvent {
    data class ChangeTitle(
        val note: Note,
        val newTitle: String
    ) : EditorEvent()

    data class ChangeText(
        val note: Note,
        val newText: String
    ) : EditorEvent()

    data class SaveNote(
        val note: Note
    ) : EditorEvent()

//    data class OnViewCreated(
//        val index: Int,
//        val note: Note
//    )
}
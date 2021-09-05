package com.example.notes.editor.model

import com.example.notes.model.Note

sealed class EditorAction {
    object NavigateToList : EditorAction()
}
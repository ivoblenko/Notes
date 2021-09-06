package com.example.notes.editor

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Repository
import com.example.notes.editor.model.EditorAction
import com.example.notes.editor.model.EditorEvent
import com.example.notes.editor.model.EditorState
import kotlinx.coroutines.launch

class EditorViewModel : ViewModel() {
    val note = mutableStateOf(EditorState())


    val action = MutableLiveData<EditorAction>()
    fun obtainEvent(event: EditorEvent) = viewModelScope.launch {
        when (event) {
            is EditorEvent.UpdateNote -> {
                note.value = note.value.copy(
                    note = note.value.note.copy(title = event.newTitle, text = event.newText)
                )
            }
            is EditorEvent.SaveNote -> {
                if (note.value.new) {
                    Repository.insert(note = note.value.note)
                } else {
                    Repository.update(note = note.value.note)
                }
            }
            is EditorEvent.NavigateToNoteList -> {
                action.value = EditorAction.NavigateToList
            }
        }
    }
}
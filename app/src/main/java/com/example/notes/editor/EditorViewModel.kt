package com.example.notes.editor

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Repository
import com.example.notes.editor.model.EditorAction
import com.example.notes.editor.model.EditorEvent
import com.example.notes.editor.model.EditorState
import com.example.notes.list.model.ListState
import com.example.notes.model.Note
import kotlinx.coroutines.launch

class EditorViewModel : ViewModel() {
    val note = mutableStateOf(EditorState())

    //TODO: поправить добавление текста и заголовка одновременно
    val action = MutableLiveData<EditorAction>()
    fun obtainEvent(event: EditorEvent) = viewModelScope.launch {
        when (event) {
            is EditorEvent.ChangeTitle -> {
                note.value = note.value.copy(note = note.value.copy(title = event.newTitle))
            }
            is EditorEvent.ChangeText -> {
                note.value = note.value.copy(note = note.value.copy(text = event.newText))
            }
            is EditorEvent.SaveNote -> {
                Repository.insert(note = note.value.note)
            }
        }
    }
}
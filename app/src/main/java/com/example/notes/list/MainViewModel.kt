package com.example.notes.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Repository
import com.example.notes.list.model.ListAction
import com.example.notes.list.model.ListEvent
import com.example.notes.list.model.ListState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val state = mutableStateOf(ListState())

    val action = MutableLiveData<ListAction>()

    fun obtainEvent(event: ListEvent) = viewModelScope.launch {
        when (event) {
            is ListEvent.NoteClick -> action.value =
                ListAction.NavigateToEditor(event.note)

            is ListEvent.NewNote -> action.value = ListAction.NavigateToNewEditor()

            is ListEvent.DeleteNote -> {
                Repository.delete(note = event.note)
            }

            is ListEvent.OnListCreate -> {
                state.value =
                    state.value.copy(notes = Repository.getNoteAll().toMutableList())
            }
        }
    }
}
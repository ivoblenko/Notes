package com.example.notes.editor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.notes.editor.model.EditorAction
import com.example.notes.list.MainActivity
import com.example.notes.model.Note
import com.example.notes.ui.NoteScreen

class EditorActivity : AppCompatActivity() {
    val viewModel: EditorViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val note = intent.getParcelableExtra<Note>(MainActivity.KEY_NOTE)
        var newNote = intent.getIntExtra(MainActivity.KEY_NEW, 1) == 1


        viewModel.note.value = viewModel.note.value.copy(
            new = newNote,
            note = note!!
        )


        setContent { NoteScreen(note = note, eventHandler = { viewModel.obtainEvent(it) }) }
        viewModel.action.observe(this) { obtainAction(it) }

        supportActionBar?.hide()

    }

    fun obtainAction(action: EditorAction) {
        when (action) {
            is EditorAction.NavigateToList -> onBackPressed()
        }
    }
}



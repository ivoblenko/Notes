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
        val index = intent.getIntExtra(MainActivity.KEY_INDEX, 0)
        val note = intent.getParcelableExtra<Note>(MainActivity.KEY_NOTE)
        setContent { NoteScreen(note =note, eventHandler ={viewModel.obtainEvent(it)}) }
        viewModel.action.observe(this) { obtainAction(it) }

        supportActionBar?.hide()

    }

    fun obtainAction(action: EditorAction) {
        when (action) {
            is EditorAction.NavigateToList -> onBackPressed()
        }
    }
}
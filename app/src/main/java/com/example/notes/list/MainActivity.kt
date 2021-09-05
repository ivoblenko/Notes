package com.example.notes.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.notes.editor.EditorActivity
import com.example.notes.list.model.ListAction
import com.example.notes.list.model.ListEvent
import com.example.notes.list.ui.MainScreen


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.obtainEvent(ListEvent.OnListCreate)

        setContent {
            MainScreen(notes = viewModel.state) { viewModel.obtainEvent(it) }
        }

        viewModel.action.observe(this) { obtainAction(it) }
    }

    private fun obtainAction(action: ListAction) {
        when (action) {
            is ListAction.NavigateToEditor -> {
                val intent = Intent(this, EditorActivity::class.java)
                intent.putExtra(KEY_NOTE, action.note)
                intent.putExtra(KEY_INDEX, action.index)
                startActivity(intent)
            }
            is ListAction.NavigateToNewEditor -> {
                val intent = Intent(this, EditorActivity::class.java)
                intent.putExtra(KEY_NOTE, action.note)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val KEY_NOTE = "note"
        const val KEY_INDEX = "index"
    }
}


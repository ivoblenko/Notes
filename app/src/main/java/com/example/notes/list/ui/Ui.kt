package com.example.notes.list.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.list.model.ListEvent
import com.example.notes.list.model.ListState
import com.example.notes.model.Note


@Composable
fun MainScreen(notes: State<ListState>, eventHandler: (ListEvent) -> Unit) {

    val remembered = remember {
        notes
    }
    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { eventHandler(ListEvent.NewNote) },
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.new_note),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }) {
        LazyColumn {
            items(remembered.value.notes) { item ->
                NoteItem(note = item, eventHandler = eventHandler)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, eventHandler: (ListEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .clickable { eventHandler.invoke(ListEvent.NoteClick(index = note.id, note = note)) }
    ) {
        NoteRow(
            backgroundColor = colorResource(id = R.color.topNoteBlock),
            text = note.title,
            textColor = colorResource(id = R.color.noteTitle)
        )
        NoteRow(
            backgroundColor = colorResource(id = R.color.mainNoteBlock),
            text = note.text
        )
    }
}

@Composable
fun NoteRow(
    backgroundColor: Color,
    text: String,
    textColor: Color = Color.Black
) {
    Row(
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = textColor,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    MainScreen(mutableStateOf(ListState()), {})
}
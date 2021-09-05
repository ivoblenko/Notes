package com.example.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.model.Note
import com.example.notes.editor.model.EditorEvent

@Composable
fun NoteScreen(note: Note? , eventHandler: (EditorEvent) -> Unit) {
    var title = remember { mutableStateOf(value = TextFieldValue(note?.title ?: "")) }
    var text = remember { mutableStateOf(value = TextFieldValue(note?.text ?: "")) }

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    eventHandler(EditorEvent.SaveNote(note!!))
                          },
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.topNoteBlock))

            ) {
                TextField(
                    value = title.value,
                    onValueChange = {
                        title.value = it
                        eventHandler(
                            EditorEvent.ChangeTitle(
                                note = note!!,
                                newTitle = it.text
                            )
                        )
                    },
                    label = { Text(text = "Title:") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.mainNoteBlock))

            ) {
                TextField(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                        eventHandler(
                            EditorEvent.ChangeText(
                                note = note!!,
                                newText = it.text
                            )
                        )
                    },
                    label = { Text(text = "Title:") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun NoteScreenPreview() {
//    NoteScreen(1, Note(1, "jhlkh", "hg"), {})
//}
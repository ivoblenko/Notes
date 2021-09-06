package com.example.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.model.Note
import com.example.notes.editor.model.EditorEvent

@Composable
fun NoteScreen(note: Note?, eventHandler: (EditorEvent) -> Unit) {
    var title = remember { mutableStateOf(value = TextFieldValue(note?.title ?: "")) }
    var text = remember { mutableStateOf(value = TextFieldValue(note?.text ?: "")) }

    Scaffold(topBar = {
        Row(


        ) {

            Row( modifier = Modifier
                .weight(1f)
                .background(color = Color.Transparent),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(
                    onClick = {
                        eventHandler(EditorEvent.NavigateToNoteList)
                    },
                )
                {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }

            Row( modifier = Modifier
                .weight(1f)
                .background(color = Color.Transparent),
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
                            EditorEvent.UpdateNote(
                                newTitle = it.text,
                                newText = text.value.text
                            )
                        )
                    },
                    label = { Text(text = "Title:") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = colorResource(id = R.color.mainNoteBlock))
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.mainNoteBlock))

            ) {
                TextField(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                        eventHandler(
                            EditorEvent.UpdateNote(
                                newTitle = title.value.text,
                                newText = it.text
                            )
                        )
                    },
                    label = { Text(text = "Title:") },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteScreenPreview() {
    NoteScreen(Note(1, "jhlkh", "hg"), {})
}
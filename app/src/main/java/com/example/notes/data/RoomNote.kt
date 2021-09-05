package com.example.notes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.notes.model.Note

@Entity(tableName = "note")
data class RoomNote(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String
)

fun Note.mapToRoomNote(): RoomNote = RoomNote(this.id, this.title, this.text)

fun RoomNote.mapToNote(): Note = Note(this.id, this.title, this.text)

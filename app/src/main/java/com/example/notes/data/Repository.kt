package com.example.notes.data

import android.content.Context
import androidx.room.Room
import com.example.notes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {
    lateinit var noteDao: NoteDao

    suspend fun init(context: Context) = withContext(Dispatchers.IO) {
        noteDao = Room
            .databaseBuilder(context, DataBase::class.java, "note_db")
            .fallbackToDestructiveMigration()
            .build()
            .getDao()
    }

    suspend fun getNote(id: Int) = withContext(Dispatchers.IO) {
        noteDao.getById(id).mapToNote()
    }

    suspend fun getNoteAll() = withContext(Dispatchers.IO) {
        noteDao.getAll().map { it.mapToNote() }
    }

    suspend fun insert(note: Note) = withContext(Dispatchers.IO) {
        noteDao.insert(note = RoomNote(id=System.currentTimeMillis(), title = note.title, text = note.text))
    }

    suspend fun update(note: Note) = withContext(Dispatchers.IO) {
        noteDao.update(note = note.mapToRoomNote())
    }

    suspend fun delete(note: Note) = withContext(Dispatchers.IO) {
        noteDao.delete(note = note.mapToRoomNote())
    }
}
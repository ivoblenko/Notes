package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomNote::class], version = 2)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): NoteDao
}
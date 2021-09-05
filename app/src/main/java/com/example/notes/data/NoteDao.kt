package com.example.notes.data

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<RoomNote>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Int): RoomNote

    @Insert
    fun insert(note: RoomNote)

    @Update
    fun update(note: RoomNote)

    @Delete
    fun delete(note: RoomNote)
}
package com.percobaan.tugas_ch6top3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.percobaan.tugas_ch6top3.model.Note

@Dao
interface NoteDao {
    @Insert
    fun addNote(user: Note) : Long

    @Query("SELECT * FROM notes")
    fun getNotes() : List<Note>?

    @Update
    fun updateNote(note: Note) : Int

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteNote(id: Int) : Int
}
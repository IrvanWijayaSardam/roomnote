package com.aminivan.noteroom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aminivan.noteroom.database.entity.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getDataNotes() : LiveData<List<Note>>

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

}
package com.aminivan.noteroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.aminivan.noteroom.database.dao.NoteDao
import com.aminivan.noteroom.database.NoteRoomDatabase
import com.aminivan.noteroom.database.entity.Note
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao : NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    fun insert(note : Note){
        executorService.execute{mNotesDao.insert(note)}
    }

    fun update(note: Note){
        executorService.execute { mNotesDao.update(note) }
    }

    fun delete(note: Note){
        executorService.execute { mNotesDao.delete(note) }
    }

    fun getDataNotes() : LiveData<List<Note>> = mNotesDao.getDataNotes()

    fun getImportantNotes() : LiveData<List<Note>> = mNotesDao.getImportantNotes()

}
package com.aminivan.noteroom.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aminivan.noteroom.database.dao.NoteDao
import com.aminivan.noteroom.database.entity.Note
import com.aminivan.noteroom.repository.NoteRepository

class NoteViewModel(application: Application) : ViewModel() {
    private val mNoteRepository : NoteRepository = NoteRepository(application)

    fun insert(note: Note){
        mNoteRepository.insert(note)
    }

    fun update(note: Note){
        mNoteRepository.update(note)
    }

    fun delete(note: Note){
        mNoteRepository.delete(note)
    }

    fun getDataNotes() : LiveData<List<Note>> =
        mNoteRepository.getDataNotes()

    fun getImportantNotes() : LiveData<List<Note>> =
        mNoteRepository.getImportantNotes()
}
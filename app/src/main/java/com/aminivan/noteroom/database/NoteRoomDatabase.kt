package com.aminivan.noteroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aminivan.noteroom.database.dao.NoteDao
import com.aminivan.noteroom.database.entity.Note
import com.aminivan.noteroom.repository.NoteRepository

@Database(entities = [Note::class], version = 1)
abstract  class NoteRoomDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao

    companion object {
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase{
            if (INSTANCE == null) {
                synchronized(NoteRepository::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteRoomDatabase::class.java, "db_note")
                        .build()
                }
            }
            return INSTANCE as NoteRoomDatabase
        }
    }
}
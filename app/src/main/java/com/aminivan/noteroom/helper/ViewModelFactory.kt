package com.aminivan.noteroom.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminivan.noteroom.viewmodel.NoteViewModel

class ViewModelFactory private constructor(private val mApp: Application): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory{
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(mApp) as T
        }
        throw java.lang.IllegalArgumentException("Unknow ViewModel class : ${modelClass.name}")
    }
}
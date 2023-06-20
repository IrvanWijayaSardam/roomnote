package com.aminivan.noteroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aminivan.noteroom.adapter.NoteAdapter
import com.aminivan.noteroom.databinding.ActivityImportantBinding
import com.aminivan.noteroom.databinding.ActivityMainBinding
import com.aminivan.noteroom.helper.ViewModelFactory
import com.aminivan.noteroom.viewmodel.NoteViewModel

private const val TAG = "ImportantActivity"
class ImportantActivity : AppCompatActivity() {
    lateinit var binding : ActivityImportantBinding
    private lateinit var vmNote : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImportantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmNote = obtainViewModel(this)

        fetchDataNotes()
    }

    private fun fetchDataNotes(){
        vmNote.getImportantNotes().observe(this) { allNotes ->
            if(allNotes != null){
                Log.d(TAG, "fetchDataNotes: data"+allNotes)
                val noteAdapter = NoteAdapter(allNotes)
                binding.rvFavoriteNote.layoutManager = LinearLayoutManager(this)
                binding.rvFavoriteNote.setHasFixedSize(true)
                binding.rvFavoriteNote.adapter = noteAdapter

                noteAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): NoteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteViewModel::class.java)
    }
}
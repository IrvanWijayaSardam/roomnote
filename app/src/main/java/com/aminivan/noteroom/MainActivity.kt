package com.aminivan.noteroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aminivan.noteroom.adapter.NoteAdapter
import com.aminivan.noteroom.database.entity.Note
import com.aminivan.noteroom.databinding.ActivityMainBinding
import com.aminivan.noteroom.helper.ViewModelFactory
import com.aminivan.noteroom.view.AddNotesActivity
import com.aminivan.noteroom.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    private lateinit var vmNote : NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmNote = obtainViewModel(this)


        fetchDataNotes()

        initListener()
    }

    private fun initListener() {
        binding.btnAddNote.setOnClickListener(){
            val intent = Intent(this,AddNotesActivity::class.java)
            startActivity(intent)
        }
    }


    private fun fetchDataNotes(){
        vmNote.getDataNotes().observe(this) { allNotes ->
            if(allNotes != null){
                val noteAdapter = NoteAdapter(allNotes)
                binding.rvNote.layoutManager = LinearLayoutManager(this)
                binding.rvNote.setHasFixedSize(true)
                binding.rvNote.adapter = noteAdapter

                noteAdapter.notifyDataSetChanged()

                noteAdapter.setOnItemClickCallback(object : NoteAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: Note, act: String) {
                        when(act){
                            "delete" -> {
                                vmNote.delete(data)
                                noteAdapter.notifyDataSetChanged()
                            }
                            "update" -> {
                                val bundle = Bundle();
                                bundle.putParcelable("note",data)
                                val intent = Intent(this@MainActivity,AddNotesActivity::class.java)
                                intent.putExtras(bundle)
                                startActivity(intent)
                            }
                            "isimportant" -> {
                                    vmNote.update(Note(data.id,data.title,data.content,!data.isImportant))
                                    noteAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): NoteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteViewModel::class.java)
    }
}
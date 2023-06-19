package com.aminivan.noteroom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminivan.noteroom.MainActivity
import com.aminivan.noteroom.R
import com.aminivan.noteroom.adapter.NoteAdapter
import com.aminivan.noteroom.databinding.ActivityAddNotesBinding
import com.aminivan.noteroom.databinding.ActivityMainBinding
import com.aminivan.noteroom.database.entity.Note
import com.aminivan.noteroom.helper.ViewModelFactory
import com.aminivan.noteroom.viewmodel.NoteViewModel

class AddNotesActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddNotesBinding

    //deklarasi view model
    private lateinit var vmNote : NoteViewModel

    private lateinit var note: Note

    private var navigation : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vmNote = obtainViewModel(this)

        initListener()
        getDataIntent()
    }

    private fun getDataIntent() {
        if(intent.extras != null){
            val bundle = intent.extras
            note = bundle!!.getParcelable<Note>("note")!!
            binding.edtTitle.setText(note.title)
            binding.edtContent.setText(note.content)
            binding.swIsImportant.isChecked = note.isImportant

            binding.btnSaveNote.setText("update")
            navigation = "update"
        } else {
            binding.btnSaveNote.setText("save")
            navigation = "save"
        }
    }

    private fun initListener() {
        binding.btnSaveNote.setOnClickListener {
            if(navigation.equals("save")){
                vmNote.insert(Note(0,binding.edtTitle.text.toString(),binding.edtContent.text.toString(),binding.swIsImportant.isChecked))
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            } else {
                vmNote.update(Note(note.id,binding.edtTitle.text.toString(),binding.edtContent.text.toString(),binding.swIsImportant.isChecked))
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.ivBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun obtainViewModel(activity: AppCompatActivity): NoteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteViewModel::class.java)
    }
}
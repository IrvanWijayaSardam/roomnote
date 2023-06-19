package com.aminivan.noteroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aminivan.noteroom.R
import com.aminivan.noteroom.database.entity.Note
import com.aminivan.noteroom.databinding.ItemNoteBinding

class NoteAdapter(private val allNotes: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  allNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = allNotes[position].title
        val content = allNotes[position].content
        val isImportant = allNotes[position].isImportant

        holder.binding.noteTitle.setText(title)
        holder.binding.noteContent.setText(content)
        if(isImportant){
            holder.binding.ivIsImportant.setImageResource(R.drawable.baseline_star_24)
        } else {
            holder.binding.ivIsImportant.setImageResource(R.drawable.baseline_star_border_24)
        }

        holder.binding.btnDeleteNote.setOnClickListener {
            onItemClickCallback.onItemClicked(allNotes[holder.adapterPosition],"delete")
        }
        holder.binding.btnUpdateNote.setOnClickListener {
            onItemClickCallback.onItemClicked(allNotes[holder.adapterPosition],"update")
        }
        holder.binding.ivIsImportant.setOnClickListener {
            onItemClickCallback.onItemClicked(allNotes[holder.adapterPosition],"isimportant")
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Note,act: String = "")
    }

}
package com.example.kur_1.ui.folders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kur_1.R
import com.example.kur_1.entities.Folder

class FoldersAdapter(
    private val onClick: (Folder) -> Unit
) : ListAdapter<Folder, FoldersAdapter.FolderViewHolder>(COMPARATOR) {  // Используем объект COMPARATOR

    inner class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewFolderName)

        fun bind(folder: Folder) {
            nameTextView.text = folder.name
            itemView.setOnClickListener { onClick(folder) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_folder, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Folder>() {
            override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean =
                oldItem == newItem
        }
    }
}
package com.example.kur_1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kur_1.data.FolderRepository
import com.example.kur_1.entities.Folder
import kotlinx.coroutines.launch

class FolderViewModel(private val repository: FolderRepository) : ViewModel() {
    val allFolders: LiveData<List<Folder>> = repository.allFolders

    fun createFolder(name: String) = viewModelScope.launch {
        if (name.isNotBlank()) {
            val folder = Folder(name = name)
            repository.insert(folder)
        }
    }
}
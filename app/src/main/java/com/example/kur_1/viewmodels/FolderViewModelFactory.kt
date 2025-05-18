package com.example.kur_1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kur_1.data.FolderRepository

class FolderViewModelFactory(private val repository: FolderRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FolderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FolderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
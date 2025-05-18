package com.example.kur_1.ui.append

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kur_1.data.AppDatabase

class AppendViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppendViewModel::class.java)) {
            return AppendViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
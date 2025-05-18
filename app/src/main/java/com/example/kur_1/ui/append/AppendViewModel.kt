package com.example.kur_1.ui.append

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kur_1.entities.Book
import com.example.kur_1.data.AppDatabase
import kotlinx.coroutines.launch

class AppendViewModel(private val database: AppDatabase) : ViewModel() {

    fun insertBook(book: Book) {
        viewModelScope.launch {
            database.bookDao().insertBook(book)
        }
    }
}
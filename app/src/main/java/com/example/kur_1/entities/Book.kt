package com.example.kur_1.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val pageCount: Int,
    val currentPage: Int,
    val genre: String,
    val folder: String
)
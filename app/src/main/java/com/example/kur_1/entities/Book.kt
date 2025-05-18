// Book.kt
package com.example.kur_1.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val genre: String,
    val totalPages: Int,
    val pagesRead: Int,
    val rating: Float = 0f,
    val review: String = ""
): Parcelable
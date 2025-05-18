package com.example.kur_1.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class Folder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // Автоинкрементируемый ID
    val name: String,  // Название папки
    // Дополнительные поля при необходимости:
    val createdAt: Long = System.currentTimeMillis()
) {
}
package com.example.kur_1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kur_1.entities.Folder

@Dao
interface FolderDao {
    @Insert
    suspend fun insert(folder: Folder)

    @Query("SELECT * FROM folders ORDER BY name ASC")
    fun getAllFolders(): LiveData<List<Folder>>

    @Query("SELECT * FROM folders WHERE id = :folderId")
    suspend fun getFolderById(folderId: Int): Folder?
}
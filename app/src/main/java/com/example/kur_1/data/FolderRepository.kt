package com.example.kur_1.data

import androidx.lifecycle.LiveData
import com.example.kur_1.entities.Folder

class FolderRepository(private val folderDao: FolderDao) {
    val allFolders: LiveData<List<Folder>> = folderDao.getAllFolders()


    suspend fun insert(folder: Folder) {
        folderDao.insert(folder)
    }

    suspend fun getFolderById(folderId: Int): Folder? {
        return folderDao.getFolderById(folderId)
    }
}
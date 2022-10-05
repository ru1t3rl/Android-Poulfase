package com.ru1t3rl.poulfase_m3.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.ru1t3rl.poulfase_m3.dao.GroupDao
import com.ru1t3rl.poulfase_m3.database.GroupDatabase
import com.ru1t3rl.poulfase_m3.model.Group

class GroupRepository(context: Context) {
    private var groupDao: GroupDao

    init {
        val database = GroupDatabase.getDatabase(context)
        groupDao = database!!.groupDao()
    }

    fun getAllGroups(): LiveData<List<Group>> {
        return groupDao.getAllGroups()
    }

    suspend fun insertGroup(group: Group) {
        groupDao.insertGroup(group)
    }

    suspend fun deleteGroup(group: Group) {
        groupDao.deleteGroup(group)
    }

    suspend fun deleteAllGroups() {
        groupDao.deleteAllGroups()
    }
}
package com.ru1t3rl.poulfase_m3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ru1t3rl.poulfase_m3.model.Group
import com.ru1t3rl.poulfase_m3.repository.GroupRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val groupRepository = GroupRepository(application.applicationContext)

    val groups: LiveData<List<Group>> = groupRepository.getAllGroups()

    fun insertGroup(group: Group) {
        ioScope.launch {
            groupRepository.insertGroup(group)
        }
    }

    fun deleteGroup(group: Group) {
        ioScope.launch {
            groupRepository.deleteGroup(group)
        }
    }

    fun deleteAllGroups() {
        ioScope.launch {
            groupRepository.deleteAllGroups()
        }
    }
}
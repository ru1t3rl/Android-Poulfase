package com.ru1t3rl.poulfase_m3.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ru1t3rl.poulfase_m3.model.Group

@Dao
interface GroupDao {
    fun getAllGroups(): LiveData<List<Group>>

    @Insert
    suspend fun insertGroup(group: Group) {

    }

    @Delete
    suspend fun deleteGroup(group: Group) {

    }

    @Query("DELETE FROM groupTable")
    suspend fun deleteAllGroups() {

    }
}
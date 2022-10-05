package com.ru1t3rl.poulfase_m3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ru1t3rl.poulfase_m3.model.TeamItem

class TeamRespository {
    private val _teams: MutableLiveData<List<TeamItem>> = MutableLiveData()

    val teams: MutableLiveData<List<TeamItem>>
        get() = _teams

    fun getTeam(teamName: String): TeamItem? {
        _teams.value?.let {
            val newTeams = it.toMutableList()
            return newTeams.find { it.name == teamName }
        }
        return null
    }

    fun getTeams() : List<TeamItem> {
        return _teams.value ?: listOf()
    }
}
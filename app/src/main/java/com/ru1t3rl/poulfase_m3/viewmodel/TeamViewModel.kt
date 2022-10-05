package com.ru1t3rl.poulfase_m3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ru1t3rl.poulfase_m3.model.TeamItem
import com.ru1t3rl.poulfase_m3.repository.TeamRespository

class TeamViewModel {
    private val teamRepository = TeamRespository()

    val teams: MutableLiveData<List<TeamItem>> = teamRepository.teams

    private val _teams = MutableLiveData<List<TeamItem>>().apply() {
        value = teamRepository.getTeams()
    }
}
package com.ru1t3rl.poulfase_m3.model

import androidx.room.Entity

@Entity(tableName = "groupTable")
data class Group(
    val name: String,
    val teams: List<Team>,
    val matches: List<List<Match>> = listOf()
) {
    fun getTeam(teamName: String): Team? {
        return teams.find { it.name == teamName }
    }

    // TODO: Get match based on team name and match number
    fun getMatch(teamName: String, matchNumber: Int): Match? {
        if(matchNumber > matches.size) {
            return null
        }

        val match = matches[matchNumber - 1]
        return match.find { it.homeTeam.name == teamName || it.awayTeam.name == teamName }
    }
}
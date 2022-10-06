package com.ru1t3rl.poulfase_m3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groupTable")
data class Group(
    @ColumnInfo(name="teamName")
    val name: String,

    @ColumnInfo(name="teams")
    val teams: List<Team>,

    @ColumnInfo(name="matches")
    var matches: List<List<Match>> = listOf(),

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long? = null
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
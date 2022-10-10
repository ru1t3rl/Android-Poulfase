package com.ru1t3rl.poutlje.model

data class Team (
    val name: String,
    val strength: Int,
    var goals: Int = 0,
    var goalsAgainst: Int = 0,
    var points: Int = 0,
    var played: Int = 0
    ) {
    fun getGoalDifference() : Int {
        return goals - goalsAgainst
    }
}
package com.ru1t3rl.poulfase_m3.model

data class Team(
    var name: String,
    var strength: Int,
    var points: Int = 0,
    var goals: Int = 0,
    var goalsAgainst: Int = 0,
    var matchesPlayed: Int = 0,
) {
    val goalDifference: Int
        get() {
            return goals - goalsAgainst
        }
}
package com.ru1t3rl.poulfase_m3.model

data class TeamItem(
    var name: String,
    var logo: String,
    var points: Int,
    var goals: Int,
    var goalsAgainst: Int,
    var goalDifference: Int,
    var matchesPlayed: Int,
    var strength: Int
) {
}
package com.ru1t3rl.poulfase_m3.model

data class MatchItem (
    val homeTeam: TeamItem,
    val awayTeam: TeamItem,
    val homeScore: Int,
    val awayScore: Int,
    val time: Int
        ) {
}
package com.ru1t3rl.poulfase_m3.model

data class Match (
    val homeTeam: Team,
    val awayTeam: Team,
    val homeScore: Int,
    val awayScore: Int,
    val time: Int
        ) {
}
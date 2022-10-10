package com.ru1t3rl.poutlje.model

import kotlin.random.Random

val MAX_STRENGTH_CALCULATIONS = 3000

data class Match (
    val homeTeam: Team,
    val awayTeam: Team,
    var homeScore: Int = 0,
    var awayScore: Int = 0,
    var time: Int = 0
) {
    fun simulate(duration: Int) {
        val strengthDifference = kotlin.math.sqrt((homeTeam.strength - awayTeam.strength) - (homeTeam.strength - awayTeam.strength).toDouble())
        val homeTeamChanges = homeTeam.strength + strengthDifference
        val awayTeamChanges = awayTeam.strength - strengthDifference

        for(i in 1..duration) {
            time = i

            Random.nextInt(0, MAX_STRENGTH_CALCULATIONS).let {
                if(it < homeTeamChanges) {
                    homeScore++
                }
            }

            Random.nextInt(0, MAX_STRENGTH_CALCULATIONS).let {
                if(it < awayTeamChanges) {
                    homeScore++
                }
            }
        }

        homeTeam.goalsAgainst += awayScore
        homeTeam.goals += homeScore

        awayTeam.goalsAgainst += homeScore
        awayTeam.goals += awayScore

        if(homeScore > awayScore)
        {
            homeTeam.points += 3
        }
        else if(awayScore > homeScore)
        {
            awayTeam.points += 3
        } else {
            homeTeam.points++
            awayTeam.points++
        }

        homeTeam.played++
        awayTeam.played++
    }
}
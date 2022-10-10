package com.ru1t3rl.poutlje.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ru1t3rl.poutlje.R
import java.util.*


class Group(
    var teams: MutableLiveData<List<Team>>,
    var matches: List<List<Match>> = listOf(),
    var round: Int = 1
) {
    private var _roundsPlayed = 0
    val roundsPlayed: Int = _roundsPlayed

    var aSide = mutableListOf<Team>()
    var bSide = mutableListOf<Team>()

    fun reorderTeams() {
        val newOrder = mutableListOf<Team>()
        for(iTeam in teams.value!!.indices){
            newOrder.add(teams.value!![iTeam])

            for(iComparison in (0 until newOrder.size - 1).reversed()) {
                if(newOrder[iComparison + 1].points > newOrder[iComparison].points) {
                    Collections.swap(newOrder, iComparison + 1, iComparison)
                }else if(newOrder[iComparison + 1].getGoalDifference() > newOrder[iComparison].getGoalDifference())
                    Collections.swap(newOrder, iComparison + 1, iComparison)
            }
        }
        teams.value = newOrder
    }

    fun simulateRound(context: Context?)
    {
        // SHow snackbar
        if(_roundsPlayed >= matches.size) {
            Toast.makeText(
                context,
                R.string.error_max_rounds,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        for(iMatch in matches[_roundsPlayed].indices){
            matches[_roundsPlayed][iMatch].simulate(90)
        }

        _roundsPlayed++
        round = _roundsPlayed
    }

    fun generateMatches() {
        val matches = mutableListOf<List<Match>>()
        var round: MutableList<Match>

        aSide.addAll(teams.value!!)
        bSide.addAll(teams.value!!.reversed())

        for (iRound in 0 until teams.value!!.size - 1) {
            round = mutableListOf()
            for (iMatch in 0 until teams.value!!.size / 2) {
                round.add(
                    Match(
                        aSide[iMatch],
                        bSide[iMatch]
                    )
                )
            }
            matches.add(round)

            aSide = rotateRight(aSide)
            bSide = rotateLeft(bSide)
        }

        this.matches = matches
    }

    fun rotateRight(teams: MutableList<Team>): MutableList<Team> {
        var tempTeamOld = teams[0]
        var tempTeamNew: Team

        // Move all elements to the right, but keep the first element fixed
        for (iTeam in 0 until teams.size) {
            if (iTeam + 1 >= teams.size) {
                tempTeamNew = teams[1]
                teams[1] = tempTeamOld
                tempTeamOld = tempTeamNew
            } else {
                tempTeamNew = teams[iTeam + 1]
                teams[iTeam + 1] = tempTeamOld
                tempTeamOld = tempTeamNew
            }
        }

        return teams
    }

    fun rotateLeft(teams: MutableList<Team>): MutableList<Team> {
        val first = teams[0]

        // Rotate the first half of the list
        for (i in 0 until teams.size / 2) {
            teams[i] = teams[i + 1]
        }

        // Replace the second half with the first half of a, but reversed
        for (i in teams.size / 2 until teams.size) {
            teams[i] = aSide[teams.size / 2 - (i - teams.size / 2 + 1)]
        }

        return teams
    }
}
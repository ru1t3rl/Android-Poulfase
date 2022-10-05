package com.ru1t3rl.poulfase_m3.model

data class GroupItem(
    val name: String,
    val teams: List<TeamItem>,
    val matches: List<List<MatchItem>>
) {
    fun getTeam(teamName: String): TeamItem? {
        return teams.find { it.name == teamName }
    }

    // TODO: Get match based on team name and match number
    fun getMatch(teamName: String, matchNumber: Int): MatchItem? {
        if(matchNumber > matches.size) {
            return null
        }

        val match = matches[matchNumber - 1]
        return match.find { it.homeTeam.name == teamName || it.awayTeam.name == teamName }
    }
}
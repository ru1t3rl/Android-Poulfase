package com.ru1t3rl.poulfase_m3

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

import com.ru1t3rl.poulfase_m3.model.Team

public class Converters {

    @TypeConverter
    public fun teamToJsonString(team: Team) : String {
        return Gson().toJson(team)
    }

    @TypeConverter
    public fun stringToTeam(info: String) : Team {
        return Gson().fromJson(info, Team::class.java)
    }

    @TypeConverters
    public fun teamListToJsonString(group: java.util.List<com.ru1t3rl.poulfase_m3.model.Team>) : String {
        return Gson().toJson(group)
    }

    @TypeConverters
    public fun stringToTeamList(info: String?) : List<Team> {
        return Gson().fromJson(info, java.util.List<com.ru1t3rl.poulfase_m3.model.Team>().javaClass)
    }
}
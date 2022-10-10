package com.ru1t3rl.poutlje.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ru1t3rl.poutlje.R
import com.ru1t3rl.poutlje.databinding.ItemTeamBinding
import com.ru1t3rl.poutlje.model.Team

class TeamAdapter(private val teams: List<Team>):
        RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTeamBinding.bind(itemView)

        fun dataBind(team: Team) {
            binding.tvTeamName.text = team.name
            binding.tvPlayed.text = team.played.toString()
            binding.tvGoals.text = team.goals.toString()
            binding.tvGoalsAgainst.text = team.goalsAgainst.toString()
            binding.tvGoalDifference.text = team.getGoalDifference().toString()
            binding.tvPoints.text = team.points.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(teams[position])
    }
}
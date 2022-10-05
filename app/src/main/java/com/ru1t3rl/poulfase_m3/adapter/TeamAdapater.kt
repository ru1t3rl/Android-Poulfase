package com.ru1t3rl.poulfase_m3.adapter

import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ru1t3rl.poulfase_m3.R
import com.ru1t3rl.poulfase_m3.databinding.ItemTeamBinding
import com.ru1t3rl.poulfase_m3.model.Team

class TeamAdapater(private val teams: List<Team>) :
    RecyclerView.Adapter<TeamAdapater.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val binding = ItemTeamBinding.bind(itemView)

            fun dataBind(team: Team) {
                // TODO Link logo
                binding.tvTeamName.text = team.name
                binding.tvGoals.text = team.goals.toString()
                binding.tvGoalsAgainst.text = team.goalsAgainst.toString()
                binding.tvGoalsDifference.text = team.goalDifference.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            from(parent.context).inflate(R.layout.item_team, parent,false)
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(teams[position])
    }
}
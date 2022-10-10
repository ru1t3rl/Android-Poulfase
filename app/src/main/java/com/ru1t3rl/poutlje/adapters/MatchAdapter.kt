package com.ru1t3rl.poutlje.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ru1t3rl.poutlje.R
import com.ru1t3rl.poutlje.databinding.ItemMatchBinding
import com.ru1t3rl.poutlje.model.Match

class MatchAdapter(private val matches: List<Match>):
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMatchBinding.bind(itemView)

        fun dataBind(match: Match) {
            binding.tvHomeName.text = match.homeTeam.name
            binding.tvHomeScore.text = match.homeScore.toString()
            binding.tvAwayName.text = match.awayTeam.name
            binding.tvAwayScore.text = match.awayScore.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(matches[position])
    }
}
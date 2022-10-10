package com.ru1t3rl.poutlje

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ru1t3rl.poutlje.adapters.MatchAdapter
import com.ru1t3rl.poutlje.adapters.TeamAdapter
import com.ru1t3rl.poutlje.databinding.FragmentFirstBinding
import com.ru1t3rl.poutlje.model.Group
import com.ru1t3rl.poutlje.model.Match
import com.ru1t3rl.poutlje.model.Team

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var round = 1

    private val group = Group(
        MutableLiveData(
            listOf(
                Team("Team_1", 40),
                Team("Team_2", 90),
                Team("Team_3", 75),
                Team("Team_4", 30),
                Team("Team_5", 75),
                Team("Team_6", 30),
                Team("Team_7", 60),
                Team("Team_8", 80)
            )
        )
    )
    private var teamAdapter = TeamAdapter(group.teams.value!!)

    private var matches: List<Match> = listOf()
    private var matchAdapter = MatchAdapter(matches)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        round = group.round
        binding.tvRoundTitle.text = String.format(getString(R.string.round), round)

        linkButtons()
        initRv()
        generateMatches()
        observeTeams()
    }

    private fun linkButtons() {
        binding.btnSimulate.setOnClickListener {
            group.simulateRound(context)
            group.reorderTeams()

            round = group.round

            if (round <= group.teams.value!!.size - 2) {
                matches = group.matches[round]
                binding.tvRoundTitle.text = String.format(getString(R.string.round), round + 1)
            } else {
                binding.tvRoundTitle.text = String.format(getString(R.string.round), round)
            }
            updateMatchAdapter()
        }

        binding.btnNextRound.setOnClickListener {
            nextRound()
        }

        binding.btnPreviousRound.setOnClickListener {
            previousRound()
        }
    }

    private fun initRv() {
        binding.rvGroup.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvGroup.adapter = teamAdapter

        binding.rvMatches.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvMatches.adapter = matchAdapter
    }

    private fun nextRound() {
        round++
        round %= (group.teams.value!!.size - 1)
        binding.tvRoundTitle.text = String.format(getString(R.string.round), round + 1)

        matches = group.matches[round]
        updateMatchAdapter()
    }

    private fun previousRound() {
        if (round - 1 < 0)
            round = (group.teams.value!!.size - 2)
        else
            round--

        binding.tvRoundTitle.text = String.format(getString(R.string.round), round + 1)

        matches = group.matches[round]
        updateMatchAdapter()
    }

    private fun generateMatches() {
        group.generateMatches()

        matches = group.matches[round]
        updateMatchAdapter()
    }

    private fun updateMatchAdapter() {
        matchAdapter = MatchAdapter(matches)
        binding.rvMatches.adapter = matchAdapter
        matchAdapter.notifyItemRangeChanged(0, matches.size)
    }

    private fun observeTeams() {
        group.teams.observe(viewLifecycleOwner) {
            // I don't know why but assigning the newly ordered list and
            // notifying the adapter didn't update the order of the rv
            teamAdapter = TeamAdapter(it)
            binding.rvGroup.adapter = teamAdapter

            teamAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
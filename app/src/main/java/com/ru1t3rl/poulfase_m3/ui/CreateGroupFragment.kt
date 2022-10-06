package com.ru1t3rl.poulfase_m3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ru1t3rl.poulfase_m3.adapter.TeamAdapater
import com.ru1t3rl.poulfase_m3.databinding.FragmentCreateGroupBinding
import com.ru1t3rl.poulfase_m3.model.Group
import com.ru1t3rl.poulfase_m3.model.Team
import com.ru1t3rl.poulfase_m3.viewmodel.GroupViewModel

class CreateGroupFragment : Fragment() {
    private var _binding: FragmentCreateGroupBinding? = null
    private val binding get() = _binding!!

    private val groupViewModel: GroupViewModel by viewModels()

    private val teams = arrayListOf<Team>()
    private val teamAdapater = TeamAdapater(teams)

    var imagePath: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupButtons()
    }

    private fun setupRecyclerView() {
        binding.rvTeams.adapter = teamAdapater
        binding.rvTeams.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun setupButtons() {
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        binding.btnAddTeam.setOnClickListener {
            addTeam()
        }

        binding.btnFinish.setOnClickListener{
            finishGroup()
        }
    }

    private fun addTeam() {
        // TODO add check for empty fields

        teams.add(Team(
                binding.tfTeamName.text.toString(),
                binding.slStrength.value.toInt()
        ))

        teamAdapater.notifyDataSetChanged()

        resetFields()
    }

    private fun finishGroup() {
        groupViewModel.insertGroup(
            Group(
                binding.tfTeamName.text.toString(),
                teams
            )
        )

        // TODO Show Snackbar

        findNavController().popBackStack()
    }

    private fun resetFields() {
        binding.ivTeamLogo.setImageDrawable(null)
        binding.tfTeamName.text.clear()
        binding.slStrength.value = 0f
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
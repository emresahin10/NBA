package com.denbase.nba.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denbase.nba.R
import com.denbase.nba.data.Teams
import com.denbase.nba.databinding.FragmentTeamsBinding
import com.denbase.nba.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment(R.layout.fragment_teams) {
    private lateinit var binding: FragmentTeamsBinding
    private lateinit var recyclerViewAdapter: TeamsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel : TeamsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamsBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(TeamsViewModel::class.java)

        setupAdapter()


        getAllTeamList()


    }

    fun setupAdapter(){
        recyclerViewAdapter = TeamsAdapter(listOf())
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.apply {
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
        }

    }

    fun getAllTeamList(){
        viewModel.getAllTeams()
        viewModel.teamsList.observe(viewLifecycleOwner) {response ->
            when(response){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response?.data?.let {
                        recyclerViewAdapter.teamsList = it.data
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    Log.e("error", response.message.toString())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getAllTeamList()
    }
}
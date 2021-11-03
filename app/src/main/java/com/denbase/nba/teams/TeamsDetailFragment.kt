package com.denbase.nba.teams

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.denbase.nba.R
import com.denbase.nba.databinding.FragmentTeamsDetailBinding
import com.denbase.nba.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsDetailFragment : Fragment(R.layout.fragment_teams_detail) {
    private lateinit var binding: FragmentTeamsDetailBinding
    private lateinit var viewModel : TeamsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamsDetailBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(TeamsViewModel::class.java)
        arguments?.getInt("id")?.let {
            viewModel.getTeamWithId(it)
        }
        getTeam()

    }

    fun getTeam(){
        viewModel.teamItem.observe(requireActivity()){
            when(it){
                is Resource.Loading -> {
                    binding.pbDetail.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    val team = it.data
                    binding.apply {
                        pbDetail.visibility = View.GONE
                        txtDetailTeamAbbreviation.text = team?.abbreviation
                        txtDetailTeamCity.text = team?.city
                        txtDetailTeamConference.text = team?.conference
                        txtDetailTeamDivision.text = team?.division
                        txtDetailTeamFullName.text = team?.full_name
                        txtDetailTeamName.text = team?.name

                    }
                }
                is Resource.Error -> {
                    Log.e("errorDetailFragment", it.message.toString())
                }
            }
        }
    }

}
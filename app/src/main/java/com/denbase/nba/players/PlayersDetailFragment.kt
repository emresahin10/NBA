package com.denbase.nba.players

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denbase.nba.R
import com.denbase.nba.databinding.FragmentPlayersDetailBinding
import com.denbase.nba.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayersDetailFragment : Fragment(R.layout.fragment_players_detail) {
    private lateinit var binding: FragmentPlayersDetailBinding
    private lateinit var viewModel : PlayersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayersDetailBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(PlayersViewModel::class.java)

        arguments?.getInt("playerId")?.let {
            viewModel.getPlayerWithId(it)
        }
        getPlayer()
    }

    fun getPlayer(){
        viewModel.playerItem.observe(requireActivity()){
            when(it){
                is Resource.Loading -> {
                    binding.pbDetailPlayer.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    val player = it.data
                    binding.apply {

                        pbDetailPlayer.visibility = View.GONE
                        txtDetailPlayerFirstName.text = player?.first_name
                        txtDetailPlayerLastName.text = player?.last_name
                        txtDetailPlayerHeightFeet.text = player?.height_feet.toString()
                        txtDetailPlayerHeightInches.text = player?.height_inches.toString()
                        txtDetailPlayerPosition.text = player?.position
                        txtDetailPlayerWeightPounds.text = player?.weight_pounds.toString()
                        txtDetailPlayerTeamName.text = player?.team?.full_name

                    }
                }
                is Resource.Error -> {
                    Log.e("errorDetailFragment", it.message.toString())
                }
            }
        }
    }
}

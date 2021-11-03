package com.denbase.nba.players

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denbase.nba.R
import com.denbase.nba.databinding.FragmentPlayersBinding
import com.denbase.nba.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment : Fragment(R.layout.fragment_players) {
    private lateinit var binding: FragmentPlayersBinding
    private lateinit var recyclerViewAdapter: PlayersAdapter
    private lateinit var viewModel : PlayersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayersBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(PlayersViewModel::class.java)

        setupRecyclerView()
        setData()

    }
    private fun setupRecyclerView(){
        recyclerViewAdapter = PlayersAdapter(listOf())
        binding.rvPlayers.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
        binding.rvPlayers.adapter = recyclerViewAdapter

    }

    fun setData(){
        viewModel.getAllPlayers()
        viewModel.playersList.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    binding.pbPlayers.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    it.data?.let {
                        binding.pbPlayers.visibility = View.GONE
                        recyclerViewAdapter.playersList = it.data
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }


    override fun onResume() {
        super.onResume()
        setData()
    }


}
package com.denbase.nba.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.denbase.nba.R
import com.denbase.nba.data.Players
import com.denbase.nba.databinding.RvItemPlayersBinding

class PlayersAdapter(var playersList: List<Players>): RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    class ViewHolder(binding:RvItemPlayersBinding): RecyclerView.ViewHolder(binding.root){

        var playerFullName = binding.txtPlayerFullName
        var playerPosition = binding.txtPlayerPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemPlayersBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        playersList.get(position).let {
            holder.playerFullName.text = "${it.first_name} ${it.last_name}"
            holder.playerPosition.text = it.position
        }
        holder.itemView.setOnClickListener {
            val playerId = playersList.get(position).id
            it.findNavController().navigate(R.id.action_item_players_to_playersDetailFragment, bundleOf("playerId" to playerId))
        }
    }

    override fun getItemCount(): Int {
      return playersList.size
    }
}
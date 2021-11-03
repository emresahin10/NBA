package com.denbase.nba.teams

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.denbase.nba.R
import com.denbase.nba.data.Teams
import com.denbase.nba.databinding.RvItemTeamsBinding

class TeamsAdapter(var teamsList: List<Teams>): RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    class ViewHolder(binding: RvItemTeamsBinding): RecyclerView.ViewHolder(binding.root){

        val teamFullName = binding.txtTeamFullName
        val teamConference = binding.txtTeamConference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemTeamsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        teamsList.get(position).let {
            holder.teamFullName.text = it.full_name
            holder.teamConference.text = it.conference
        }

        Log.d("response","geliyor")

        holder.itemView.setOnClickListener {
            val id = teamsList.get(position).id
            it.findNavController().navigate(R.id.action_item_teams_to_teamsDetailFragment, bundleOf("id" to id))
        }
    }

    override fun getItemCount(): Int {
      return teamsList.size
    }
}
package com.denbase.nba.repositories

import com.denbase.nba.data.PlayerItem
import com.denbase.nba.data.Players
import com.denbase.nba.data.TeamItem
import com.denbase.nba.data.Teams
import com.denbase.nba.utils.Resource

interface BallDontLieRepository {

    suspend fun getAllTeams(): Resource<TeamItem>

    suspend fun getAllPlayers(): Resource<PlayerItem>

    suspend fun getTeamWithId(id: Int): Resource<Teams>

    suspend fun getPlayerWithId(playerId: Int): Resource<Players>
}
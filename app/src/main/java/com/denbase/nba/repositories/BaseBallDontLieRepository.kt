package com.denbase.nba.repositories

import com.denbase.nba.api.BallDontLieApi
import com.denbase.nba.data.PlayerItem
import com.denbase.nba.data.Players
import com.denbase.nba.data.TeamItem
import com.denbase.nba.data.Teams
import com.denbase.nba.utils.Resource
import java.lang.Exception

class BaseBallDontLieRepository(private val api: BallDontLieApi): BallDontLieRepository {

    override suspend fun getAllTeams(): Resource<TeamItem> {
        try {
            val response = api.getAllTeams()
            if (response.isSuccessful){
                val body = response.body()
                return Resource.Success(body)
            }
            return Resource.Error(response.message())
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }

    }

    override suspend fun getAllPlayers(): Resource<PlayerItem> {
        try {
            val response = api.getAllPlayers()
            if (response.isSuccessful){
                val body = response.body()
                return Resource.Success(body)
            }
            return Resource.Error(response.message())
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
    }

    override suspend fun getTeamWithId(id: Int): Resource<Teams> {
        try {
            val response = api.getTeamWithId(id)
            if (response.isSuccessful){
                val body = response.body()
                return Resource.Success(body)
            }
            return Resource.Error(response.message())
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }

    }

    override suspend fun getPlayerWithId(playerId: Int): Resource<Players> {
        try {
            val response = api.getPlayerWithId(playerId)
            if (response.isSuccessful){
                val body = response.body()
                return Resource.Success(body)
            }
            return Resource.Error(response.message())
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
    }


}
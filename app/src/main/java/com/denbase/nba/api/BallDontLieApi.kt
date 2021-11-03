package com.denbase.nba.api

import com.denbase.nba.data.PlayerItem
import com.denbase.nba.data.Players
import com.denbase.nba.data.TeamItem
import com.denbase.nba.data.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BallDontLieApi {
    @GET("teams")
    suspend fun getAllTeams(): Response<TeamItem>

    @GET("players")
    suspend fun getAllPlayers(): Response<PlayerItem>

    @GET("teams/{id}")
    suspend fun getTeamWithId(@Path("id")id: Int): Response<Teams>

    @GET("players/{id}")
    suspend fun getPlayerWithId(@Path("id")playerId: Int): Response<Players>
}
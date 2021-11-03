package com.denbase.nba.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denbase.nba.data.PlayerItem
import com.denbase.nba.data.Players
import com.denbase.nba.repositories.BallDontLieRepository
import com.denbase.nba.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val repository: BallDontLieRepository
): ViewModel() {

    private var _playersLiveData = MutableLiveData<Resource<PlayerItem>>()
    var playersList: LiveData<Resource<PlayerItem>> = _playersLiveData

    private var _playersData = MutableLiveData<Resource<Players>>()
    var playerItem: LiveData<Resource<Players>> = _playersData




    fun getAllPlayers(){
        _playersLiveData.postValue(Resource.Loading())
        viewModelScope.launch {
            val playersList = repository.getAllPlayers()
            _playersLiveData.postValue(playersList)
        }
    }

    fun getPlayerWithId(playerId: Int){
        _playersData.postValue(Resource.Loading())
        viewModelScope.launch {
            val player = repository.getPlayerWithId(playerId)
            _playersData.postValue(player)
        }
    }

}
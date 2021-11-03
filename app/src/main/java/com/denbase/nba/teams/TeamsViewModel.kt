package com.denbase.nba.teams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denbase.nba.data.TeamItem
import com.denbase.nba.data.Teams
import com.denbase.nba.repositories.BallDontLieRepository
import com.denbase.nba.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val repository: BallDontLieRepository
): ViewModel() {

    private var _teamsLiveData = MutableLiveData<Resource<TeamItem>>()
    var teamsList: LiveData<Resource<TeamItem>> = _teamsLiveData

    private var _teamData = MutableLiveData<Resource<Teams>>()
    var teamItem: LiveData<Resource<Teams>> = _teamData


    fun getAllTeams(){
        _teamsLiveData.postValue(Resource.Loading())

        viewModelScope.launch {
            Log.d("response","repository geliyor")
            _teamsLiveData.postValue(repository.getAllTeams())
        }
    }

    fun getTeamWithId(id: Int){
        _teamData.postValue(Resource.Loading())

        viewModelScope.launch {
            _teamData.postValue(repository.getTeamWithId(id))
        }
    }
}
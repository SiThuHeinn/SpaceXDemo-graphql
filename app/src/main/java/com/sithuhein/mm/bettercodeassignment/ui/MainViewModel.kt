package com.sithuhein.mm.bettercodeassignment.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sithuhein.mm.domain.model.LaunchDetails
import com.sithuhein.mm.domain.model.LaunchesPast
import com.sithuhein.mm.domain.repository.LaunchesListDataRepository
import com.sithuhein.mm.domain.resultState.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
   private val repository: LaunchesListDataRepository
) : ViewModel() {


    private val _launchesPastList = MutableLiveData<ResultStatus<List<LaunchesPast>>>()
    val launchesPastList : LiveData<ResultStatus<List<LaunchesPast>>>
        get() = _launchesPastList


    private val _launchDetails = MutableLiveData<ResultStatus<LaunchDetails>>()
    val launchDetails : LiveData<ResultStatus<LaunchDetails>>
        get() = _launchDetails


    private val _launchId = MutableLiveData<String>()
    val launchId : LiveData<String>
        get() = _launchId


    init {
        getLaunchesPastList()
        getLaunchDetails("109")
    }



    private fun getLaunchesPastList() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.fetchLaunchesPastList()
            _launchesPastList.postValue(data)
        }
    }

    private fun getLaunchDetails(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val launchDetails = repository.fetchLaunchDetails(id)
           _launchDetails.postValue(launchDetails)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}
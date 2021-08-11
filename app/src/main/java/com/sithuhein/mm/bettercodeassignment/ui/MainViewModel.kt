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
import javax.inject.Singleton


/**
 *... @author Si Thu Hein
 *... Repository pattern has been used instead of connecting with UseCases
 *... in this MVVM design pattern with Clean Architecture
 *... since I thought that it's just adding one unnecessary step. ( personal point of view )
 */
@Singleton //... @Singleton Scope is used as there is only one viewModel in Entire App
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
    }

    fun setLaunchId(id : String) {
        _launchId.postValue(id)
    }

    fun getLaunchesPastList() {
        viewModelScope.launch(Dispatchers.IO) {
            _launchesPastList.postValue(ResultStatus.Loading())
            val data = repository.fetchLaunchesPastList()
            _launchesPastList.postValue(data)
        }
    }

    fun getLaunchDetails(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            _launchDetails.postValue(ResultStatus.Loading())
            val launchDetails = repository.fetchLaunchDetails(id)
           _launchDetails.postValue(launchDetails)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}
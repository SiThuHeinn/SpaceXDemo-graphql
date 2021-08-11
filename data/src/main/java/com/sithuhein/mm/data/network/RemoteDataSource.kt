package com.sithuhein.mm.data.network

import com.sithuhein.mm.domain.model.LaunchDetails
import com.sithuhein.mm.domain.model.LaunchesPast
import com.sithuhein.mm.domain.resultState.ResultStatus

interface RemoteDataSource {
    suspend fun fetchLaunchesPastList() : ResultStatus<List<LaunchesPast>>
    suspend fun fetchLaunchDetails(id : String) : ResultStatus<LaunchDetails>
}
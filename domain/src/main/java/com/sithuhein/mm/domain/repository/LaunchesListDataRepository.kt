package com.sithuhein.mm.domain.repository

import com.sithuhein.mm.domain.model.LaunchDetails
import com.sithuhein.mm.domain.model.LaunchesPast
import com.sithuhein.mm.domain.resultState.ResultStatus


interface LaunchesListDataRepository {
    suspend fun fetchLaunchesPastList() : ResultStatus<List<LaunchesPast>>
    suspend fun fetchLaunchDetails(id : String) : ResultStatus<LaunchDetails>
}
package com.sithuhein.mm.data.repository

import com.sithuhein.mm.data.network.RemoteDataSource
import com.sithuhein.mm.domain.model.LaunchDetails
import com.sithuhein.mm.domain.model.LaunchesPast
import com.sithuhein.mm.domain.repository.LaunchesListDataRepository
import com.sithuhein.mm.domain.resultState.ResultStatus
import javax.inject.Inject

class LaunchesListDataRepositoryImpl @Inject constructor(
    private val remote : RemoteDataSource
) : LaunchesListDataRepository {

    override suspend fun fetchLaunchesPastList(): ResultStatus<List<LaunchesPast>> {
        return remote.fetchLaunchesPastList()
    }

    override suspend fun fetchLaunchDetails(id: String): ResultStatus<LaunchDetails> {
        return remote.fetchLaunchDetails(id)
    }
}
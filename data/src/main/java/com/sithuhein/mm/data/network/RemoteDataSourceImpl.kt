package com.sithuhein.mm.data.network

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.sithuhein.mm.data.LaunchDetailsQuery
import com.sithuhein.mm.data.LaunchesPastListQuery
import com.sithuhein.mm.data.mapper.ModelMapper
import com.sithuhein.mm.data.utils.NetworkConnection
import com.sithuhein.mm.domain.model.LaunchDetails
import com.sithuhein.mm.domain.model.LaunchesPast
import com.sithuhein.mm.domain.resultState.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val network: NetworkConnection,
    private val apolloClient: ApolloClient
) : RemoteDataSource {

    override suspend fun fetchLaunchesPastList(): ResultStatus<List<LaunchesPast>> {
        return withContext(Dispatchers.IO) {
            if (network.isConnected()) {
                val response = try {
                    apolloClient.query(LaunchesPastListQuery()).await()
                } catch (e: ApolloException) {
                    Log.d("MainActivity ", "LocalizedMessage ${e.localizedMessage} | ${e.message}")
                    return@withContext ResultStatus.Error(e.localizedMessage ?: e.message)
                }
                if (response.data != null && response.data?.launchesPast != null) {
                    val mappedModel = ModelMapper.convert(response.data?.launchesPast!!)
                    return@withContext ResultStatus.Success(mappedModel)
                } else {
                    return@withContext ResultStatus.Error("Something went wrong!")
                }
            } else {
                return@withContext ResultStatus.NetworkConnection("No Internet Connection")
            }
        }
    }

    override suspend fun fetchLaunchDetails(id: String): ResultStatus<LaunchDetails> {
        return withContext(Dispatchers.IO) {
            if (network.isConnected()) {
                val response = try {
                    apolloClient.query(LaunchDetailsQuery(id)).await()
                } catch (e: ApolloException) {
                    return@withContext ResultStatus.Error(e.localizedMessage ?: e.message)
                }
                if (response.data != null && response.data?.launch != null) {
                    val mappedModel = ModelMapper.convert(response.data?.launch!!)
                    return@withContext ResultStatus.Success(mappedModel)
                } else {
                    return@withContext ResultStatus.Error("Something went wrong!")
                }
            } else {
                return@withContext ResultStatus.NetworkConnection("No Internet Connection")
            }
        }
    }
}
package com.sithuhein.mm.bettercodeassignment.di

import com.sithuhein.mm.data.network.RemoteDataSource
import com.sithuhein.mm.data.network.RemoteDataSourceImpl
import com.sithuhein.mm.data.repository.LaunchesListDataRepositoryImpl
import com.sithuhein.mm.domain.repository.LaunchesListDataRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {
    @Binds
    abstract fun provideRemoteDataSource(impl : RemoteDataSourceImpl) : RemoteDataSource

    @Binds
    abstract fun provideLaunchListDataRepository(impl : LaunchesListDataRepositoryImpl) : LaunchesListDataRepository
}

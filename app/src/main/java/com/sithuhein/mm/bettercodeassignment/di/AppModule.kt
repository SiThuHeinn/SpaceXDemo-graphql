package com.sithuhein.mm.bettercodeassignment.di

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.sithuhein.mm.data.network.RemoteDataSource
import com.sithuhein.mm.data.network.RemoteDataSourceImpl
import com.sithuhein.mm.data.repository.LaunchesListDataRepositoryImpl
import com.sithuhein.mm.data.utils.ResponseInterceptor
import com.sithuhein.mm.domain.repository.LaunchesListDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    private val API_BASE_URL = "https://api.spacex.land/graphql/"


    //... due to simplicity of this app. @Single is only used instead of custom scopes
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .addInterceptor(ResponseInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Singleton
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient = ApolloClient.builder()
        .serverUrl(API_BASE_URL)
        .okHttpClient(okHttpClient)
        .build()


}
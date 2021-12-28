package com.reggya.dashboard.domain

import com.reggya.dashboard.data.RemoteDataSource
import com.reggya.dashboard.data.Repository
import com.reggya.dashboard.data.service.ApiConfig

object Injection {

    private fun provideRepository():IRepository{
        val remoteData = RemoteDataSource.getInstance(ApiConfig.provideApiServiceData())
        val remoteNews = RemoteDataSource.getInstance(ApiConfig.provideApiServiceNews())
        return Repository.getInstance(remoteData, remoteNews)
    }

    fun provideUseCase(): UseCase{
        val repository = provideRepository()
        return UseCase(repository)
    }

}
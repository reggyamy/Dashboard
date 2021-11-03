package com.reggya.dashboard.ui

import android.content.Context
import com.reggya.dashboard.data.ApiConfig
import com.reggya.dashboard.data.RemoteDataSource
import com.reggya.dashboard.data.Repository
import com.reggya.dashboard.domain.IRepository
import com.reggya.dashboard.domain.UseCase

object Injection {

    private fun provideRepository():IRepository{
        val remoteData = RemoteDataSource.getInstance(ApiConfig.provideApiServiceData())
        val remoteNews = RemoteDataSource.getInstance(ApiConfig.provideApiServiceNews())
        return Repository.getInstance(remoteNews, remoteData)
    }

    fun provideUseCase(
        context: Context
    ): UseCase{
        val repository = provideRepository()
        return UseCase(repository)
    }

}
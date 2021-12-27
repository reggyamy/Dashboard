package com.reggya.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.reggya.dashboard.data.ApiResponse
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.domain.UseCase

class HomeViewModel(
    private val useCase: UseCase
): ViewModel(){

     fun getData(year: String) = LiveDataReactiveStreams.fromPublisher(useCase.getData(year))
     val getNews: LiveData<ApiResponse<List<BeritaItem?>?>> =
         LiveDataReactiveStreams.fromPublisher(useCase.getNews())
}
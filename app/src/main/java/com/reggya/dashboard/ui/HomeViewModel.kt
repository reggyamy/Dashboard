package com.reggya.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.reggya.dashboard.data.ApiResponse
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.Response
import com.reggya.dashboard.domain.UseCase

class HomeViewModel(
    useCase: UseCase
): ViewModel(){

//     val getData = LiveDataReactiveStreams.fromPublisher(useCase.getData())
     val getNews: LiveData<ApiResponse<List<BeritaItem?>?>> =
         LiveDataReactiveStreams.fromPublisher(useCase.getNews())
}
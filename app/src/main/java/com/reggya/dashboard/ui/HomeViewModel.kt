package com.reggya.dashboard.ui

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.reggya.dashboard.domain.UseCase

class HomeViewModel(
    useCase: UseCase
): ViewModel(){

//     val getData = LiveDataReactiveStreams.fromPublisher(useCase.getData())
     val getNews = LiveDataReactiveStreams.fromPublisher(useCase.getNews())

}
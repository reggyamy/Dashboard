package com.reggya.dashboard.domain

import com.reggya.dashboard.CovidResponseItem
import com.reggya.dashboard.data.ApiResponse
import com.reggya.dashboard.data.ArticlesItem
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.XMLBerita
import io.reactivex.rxjava3.core.Flowable

class UseCase(private val iRepository: IRepository) {

//    fun getData(): Flowable<ApiResponse<List<CovidResponseItem>>> =
//        iRepository.getData()

    fun getNews(): Flowable<ApiResponse<List<BeritaItem?>?>> =
        iRepository.getNews()

}
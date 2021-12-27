package com.reggya.dashboard.domain

import com.reggya.dashboard.data.ApiResponse
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.model.DataItem
import io.reactivex.rxjava3.core.Flowable

class UseCase(private val iRepository: IRepository) {

    fun getData(year: String): Flowable<ApiResponse<List<DataItem?>?>> =
        iRepository.getData(year)

    fun getNews(): Flowable<ApiResponse<List<BeritaItem?>?>> =
        iRepository.getNews()

}
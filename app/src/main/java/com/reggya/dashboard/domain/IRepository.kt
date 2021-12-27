package com.reggya.dashboard.domain

import com.reggya.dashboard.data.ApiResponse
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.model.DataItem
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable

interface IRepository {

    fun getData(year: String): @NonNull Flowable<ApiResponse<List<DataItem?>?>>
    fun getNews(): Flowable<ApiResponse<List<BeritaItem?>?>>

}

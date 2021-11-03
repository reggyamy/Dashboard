package com.reggya.dashboard.domain

import com.reggya.dashboard.CovidResponseItem
import com.reggya.dashboard.data.ApiResponse
import com.reggya.dashboard.data.ArticlesItem
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.XMLBerita
import io.reactivex.rxjava3.core.Flowable

interface IRepository {

//    fun getData(): Flowable<ApiResponse<List<CovidResponseItem>>>
    fun getNews(): Flowable<ApiResponse<List<BeritaItem?>?>>

//    fun getNews(): Flowable<ApiResponse<List<BeritaItem?>?>>

}

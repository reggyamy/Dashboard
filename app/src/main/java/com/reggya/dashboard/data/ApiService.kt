package com.reggya.dashboard.data

import com.reggya.dashboard.CovidResponseItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

//    @GET("indonesia/provinsi")
//    fun getData(): Flowable<List<CovidResponseItem>>

    @Headers("Content-Type: application/json")
    @GET(".")
    fun getNews(
        @Query("method") method : String,
//        @Query("kategori") kategori : String,
//        @Query("hal") hal : Int,
        @Query("tipe") tipe : String
    ): Flowable<List<BeritaItem>>

}
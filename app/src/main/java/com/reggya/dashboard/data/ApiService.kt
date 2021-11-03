package com.reggya.dashboard.data

import com.reggya.dashboard.CovidResponseItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit




interface ApiService {

    @GET("indonesia/provinsi")
    fun getData(): Flowable<List<CovidResponseItem>>

//    @GET("top-headlines")
    @GET(".")
    fun getNews(
        @Query("method") method : String,
//        @Query("kategori") kategori : String,
//        @Query("hal") hal : Int,
        @Query("tipe") tipe : String
//        @Query("country") country : String,
//        @Query("apiKey") api : String
    ): Flowable<XMLBerita>


}
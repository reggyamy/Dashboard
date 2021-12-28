package com.reggya.dashboard.data.service

import com.reggya.dashboard.data.GetBeritaPerHalaman
import com.reggya.dashboard.data.model.KemiskinanResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("api/mobile/kemiskinan-all/{tahun}")
    fun getData(
        @Path("tahun") tahun : String
    ): Flowable<KemiskinanResponse>

    @GET("rest")
    fun getNews(
        @Query("method") method : String,
        @Query("kategori") kategori : String,
        @Query("hal") hal : Int,
        @Query("tipe") tipe : String
    ): Flowable<GetBeritaPerHalaman>


}
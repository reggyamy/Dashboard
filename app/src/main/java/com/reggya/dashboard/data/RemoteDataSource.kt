package com.reggya.dashboard.data

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.util.Log
import com.reggya.dashboard.data.model.DataItem
import com.reggya.dashboard.data.service.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

import okhttp3.OkHttpClient





class RemoteDataSource private constructor(private val apiService: ApiService){

    @SuppressLint("LongLogTag")
    fun getData(year : String): @NonNull Flowable<ApiResponse<List<DataItem?>?>> {
        val resultData = PublishSubject.create<ApiResponse<List<DataItem?>?>>()

        val client = apiService.getData(year)
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it.data
                if (dataArray != null) {
                    resultData.onNext(
                        if (dataArray.isNotEmpty()) ApiResponse.success(dataArray)
                        else ApiResponse.error("error")
                    )
                }
            }, { error->
                resultData.onNext(ApiResponse.error(error.message.toString()))
                Log.e("RemoteDataSource getData error", error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("LongLogTag")
    fun getNews(): Flowable<ApiResponse<List<BeritaItem?>?>>{
        val resultData = PublishSubject.create<ApiResponse<List<BeritaItem?>?>>()

        val client = apiService.getNews("getBeritaPerHalaman",  "komisi8", 1, "xml")
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val data = it.response
                if (data != null){
                    resultData.onNext(
                        if (data.isNotEmpty()) ApiResponse.success(data)
                        else ApiResponse.loading()
                    )
                }
            }, { error ->
                resultData.onNext(ApiResponse.error(error.message.toString()))
                Log.e("RemoteDataSource getNews error", error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiService)
            }
    }
}

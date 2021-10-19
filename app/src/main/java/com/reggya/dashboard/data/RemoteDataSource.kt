package com.reggya.dashboard.data

import android.annotation.SuppressLint
import android.util.Log
import com.reggya.dashboard.CovidResponseItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService){

//    private var data = MutableLiveData<List<CovidResponseItem>>()

//    @SuppressLint("LongLogTag")
//    fun getData(): Flowable<ApiResponse<List<CovidResponseItem>>>{
//        val resultData = PublishSubject.create<ApiResponse<List<CovidResponseItem>>>()
//
//        val client = apiService.getData()
//        client.subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .take(1)
//            .subscribe({
//                resultData.onNext(
//                    if (it.isNotEmpty()) ApiResponse.success(it)
//                    else ApiResponse.error("error")
//                )
//            }, { error->
//                resultData.onNext(ApiResponse.error(error.message.toString()))
//                Log.e("RemoteDataSource getData error", error.toString())
//            })
//        return resultData.toFlowable(BackpressureStrategy.BUFFER)
//    }

    @SuppressLint("LongLogTag")
    fun getNews(): Flowable<ApiResponse<List<BeritaItem>>>{
        val resultData = PublishSubject.create<ApiResponse<List<BeritaItem>>>()

        val client = apiService.getNews("get5BeritaFoto", "json")
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                resultData.onNext(
                    if(it.isNotEmpty()) ApiResponse.success(it)
                    else ApiResponse.error("error")
                )
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

package com.reggya.dashboard.data

import com.reggya.dashboard.data.model.DataItem
import com.reggya.dashboard.domain.IRepository
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val remoteDataSource2: RemoteDataSource
): IRepository {

    override fun getData(year: String): Flowable<ApiResponse<List<DataItem?>?>> =
        remoteDataSource.getData(year)

    override fun getNews(): Flowable<ApiResponse<List<BeritaItem?>?>> =
        remoteDataSource2.getNews()


    companion object{
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource, remoteData2: RemoteDataSource): Repository {
            return instance ?: synchronized(this){
                instance ?: Repository(remoteData, remoteData2)
            }
        }
    }
}
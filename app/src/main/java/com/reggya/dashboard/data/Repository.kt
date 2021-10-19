package com.reggya.dashboard.data

import com.reggya.dashboard.CovidResponseItem
import com.reggya.dashboard.domain.IRepository
import io.reactivex.rxjava3.core.Flowable

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    remoteNews: RemoteDataSource
): IRepository {

//    override fun getData(): Flowable<ApiResponse<List<CovidResponseItem>>> =
//        remoteDataSource.getData()

    override fun getNews(): Flowable<ApiResponse<List<BeritaItem>>> =
        remoteDataSource.getNews()


    companion object{
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource, remoteNews: RemoteDataSource): Repository {
            return instance ?: synchronized(this){
                instance ?: Repository(remoteData, remoteNews)
            }
        }
    }
}
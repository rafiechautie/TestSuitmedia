package com.example.testsuitmedia.data.paging

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.testsuitmedia.data.response.DataItem
import com.example.testsuitmedia.data.retrofit.ApiConfig
import com.example.testsuitmedia.data.retrofit.ApiService

class UserRepository {
    private val apiService: ApiService = ApiConfig.getApiService()
    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}
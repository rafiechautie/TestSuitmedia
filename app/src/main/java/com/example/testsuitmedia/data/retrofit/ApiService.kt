package com.example.testsuitmedia.data.retrofit

import com.example.testsuitmedia.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsersFromApi(
        @Query("page")page: Int,
        @Query("size")size: Int
    ): UserResponse
}
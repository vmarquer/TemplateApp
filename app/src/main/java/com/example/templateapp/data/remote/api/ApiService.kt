package com.example.templateapp.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("hello-world")
    suspend fun getHellowWorld(): Response<String>

    @POST("hello-world/{id}")
    suspend fun postHellowWorld(
        @Path("id") id: Int
    )
}
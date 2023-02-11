package com.example.btechapp.data.network

import retrofit2.http.GET

interface Api {

    @GET("products")
    suspend fun getAllProducts(): ResponseList

}
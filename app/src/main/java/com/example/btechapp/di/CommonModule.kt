package com.example.btechapp.di

import com.example.btechapp.data.network.Api
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommonModule {

    fun create() = module {
        single {
            Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(Api::class.java)
        }
    }
}
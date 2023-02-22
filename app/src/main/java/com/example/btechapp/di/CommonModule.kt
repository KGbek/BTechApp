package com.example.btechapp.di

import com.example.btechapp.data.network.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommonModule {
    fun create() = module {
        single {
            val client = OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build()
            Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Api::class.java)
        }
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
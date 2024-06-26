package com.joabesantana.androidapiconsumer.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://api.themoviedb.org"

        private fun getRetrofitInstance(): Retrofit {
            if (!Companion::INSTANCE.isInitialized) {
                val http = OkHttpClient.Builder()
                INSTANCE = Retrofit.Builder().baseUrl(BASE_URL).client(http.build())
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return INSTANCE
        }

        fun <T> createService(service: Class<T>): T {
            return getRetrofitInstance().create(service)
        }
    }
}
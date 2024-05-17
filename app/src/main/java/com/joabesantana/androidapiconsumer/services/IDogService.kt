package com.joabesantana.androidapiconsumer.services

import com.joabesantana.androidapiconsumer.model.Dog
import com.joabesantana.androidapiconsumer.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface IDogService {
    @Headers(
        "Content-Type: application/json",
        "x-api-key: live_dmpsGnerefTPjX2XGbKpxZ4zzHdefPoDZ5HPdoKi0qiQ5JtUfLtf6Py2xCCs4yxA"
    )
    @GET("/v1/images/search")
    fun fetchDogs(@QueryMap params: Map<String, String>): Call<MutableList<Dog>>
}
package com.joabesantana.androidapiconsumer

import retrofit2.Call
import retrofit2.http.GET

interface IPhotoService {
    @GET("/photos")
    fun fetchPhotos(): Call<List<Photo>>
}
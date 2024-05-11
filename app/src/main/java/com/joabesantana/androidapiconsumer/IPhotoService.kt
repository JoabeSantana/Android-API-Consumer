package com.joabesantana.androidapiconsumer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface IPhotoService {
    @Headers("accept: application/json","Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NzAwNDkwNmU1OGQwMWMzNTkwMjc5Yjg2MDkxNjc2ZCIsInN1YiI6IjY1NGFkN2ZmZmQ0ZjgwMDExZWQyZTgzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.w7bnNINfJIabmAm_gRv0VlesdyZYTvWv9Cqbqim8Scc")
    @GET("/3/movie/now_playing?language=en-US&page=1")
    fun fetchPhotos(): Call<Results>
}
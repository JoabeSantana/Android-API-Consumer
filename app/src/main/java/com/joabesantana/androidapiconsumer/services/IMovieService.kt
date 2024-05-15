package com.joabesantana.androidapiconsumer.services

import com.joabesantana.androidapiconsumer.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface IMovieService {
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NzAwNDkwNmU1OGQwMWMzNTkwMjc5Yjg2MDkxNjc2ZCIsInN1YiI6IjY1NGFkN2ZmZmQ0ZjgwMDExZWQyZTgzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.w7bnNINfJIabmAm_gRv0VlesdyZYTvWv9Cqbqim8Scc"
    )
    @GET("/3/movie/now_playing")
    fun fetchPhotos(@QueryMap params: Map<String, String>): Call<Results>
}
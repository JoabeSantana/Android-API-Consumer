package com.joabesantana.androidapiconsumer.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joabesantana.androidapiconsumer.IMovieService
import com.joabesantana.androidapiconsumer.Movie
import com.joabesantana.androidapiconsumer.Results
import com.joabesantana.androidapiconsumer.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListViewModel: ViewModel() {
    val movieListLiveData = MutableLiveData<MutableList<Movie>>()

    fun fetchMovies(page: Int, concatResults: Boolean) {
        val photoService = RetrofitClient.createService(IMovieService::class.java)

        val params: Map<String, String> = mapOf("language" to "en-US", "page" to page.toString())

        val call = photoService.fetchPhotos(params)

        call.enqueue(object : Callback<Results> {

            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (response.isSuccessful) {
                    val movieResults = response.body()
                    if (movieResults != null) {
                        if(concatResults && movieListLiveData.value != null){
                            val results: MutableList<Movie> = ArrayList()
                            results.addAll(movieListLiveData.value!!)
                            results.addAll(movieResults.results)
                            movieListLiveData.value = results
                        } else {
                            movieListLiveData.value = movieResults.results
                        }
                    }
                } else {
                    println("Error to fetch Movies: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                println("Error to fetch Movies: ${t.message}")
            }
        })
    }
}
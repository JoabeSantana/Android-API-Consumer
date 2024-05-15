package com.joabesantana.androidapiconsumer.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joabesantana.androidapiconsumer.services.IMovieService
import com.joabesantana.androidapiconsumer.model.Movie
import com.joabesantana.androidapiconsumer.model.Results
import com.joabesantana.androidapiconsumer.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListViewModel : ViewModel() {

    private var movieListLiveData = MutableLiveData<MutableList<Movie>>()

    fun fetchMovies(page: Int, concatResults: Boolean) {
        val photoService = RetrofitClient.createService(IMovieService::class.java)

        val params: Map<String, String> = mapOf("language" to "en-US", "page" to page.toString())

        val call = photoService.fetchPhotos(params)

        call.enqueue(object : Callback<Results> {

            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (response.isSuccessful) {
                    val movieResults = response.body()
                    if (movieResults != null) {
                        if (concatResults && movieListLiveData.value != null) {
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

    fun getMovieListLiveData(): MutableLiveData<MutableList<Movie>> {
        return movieListLiveData
    }
}
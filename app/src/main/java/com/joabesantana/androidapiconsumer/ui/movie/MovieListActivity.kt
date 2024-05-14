package com.joabesantana.androidapiconsumer.ui.movie

import android.os.Bundle
import android.widget.AbsListView.OnScrollListener
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joabesantana.androidapiconsumer.MovieAdapter
import com.joabesantana.androidapiconsumer.databinding.ActivityListMovieBinding


class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListMovieBinding
    private lateinit var movieListViewModel: MovieListViewModel

    private lateinit var gridView: GridView
    private lateinit var adapter: MovieAdapter

    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val context: Context = this

        binding = ActivityListMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gridView = binding.idGridViewPhotos

        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        movieListViewModel.fetchMovies(page++, true)

        movieListViewModel.movieListLiveData.observe(this, Observer { moviesList ->
            if (moviesList != null) {
                adapter = MovieAdapter(this, moviesList)
                gridView.adapter = adapter
                adapter.notifyDataSetChanged()
                gridView.setSelection(moviesList.size)
            }
        })

        binding.btLoadMore.setOnClickListener {
            movieListViewModel.fetchMovies(page++, true)
            adapter.notifyDataSetChanged()
        }

//        val photoService = RetrofitClient.createService(IMovieService::class.java)
//        val call = photoService.fetchPhotos()
//
//        call.enqueue(object : Callback<Results> {
//
//            override fun onResponse(call: Call<Results>, response: Response<Results>) {
//                if (response.isSuccessful) {
//                    val movieResults = response.body()
//                    if (movieResults != null) {
//                        adapter = MovieAdapter(context, movieResults.results)
//                        gridView.adapter = adapter
//                        adapter.notifyDataSetChanged()
//                    }
//                } else {
//                    println("Error to fetch Movies: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<Results>, t: Throwable) {
//                println("Error to fetch Movies: ${t.message}")
//            }
//        })

    }
}
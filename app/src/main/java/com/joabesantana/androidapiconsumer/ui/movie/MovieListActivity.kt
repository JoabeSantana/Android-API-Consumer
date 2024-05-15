package com.joabesantana.androidapiconsumer.ui.movie

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joabesantana.androidapiconsumer.databinding.ActivityMovieListBinding


class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var viewModel: MovieListViewModel

    private lateinit var gridView: GridView
    private lateinit var adapter: MovieAdapter

    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gridView = binding.idGridViewPhotos

        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        viewModel.fetchMovies(++page, false)

        viewModel.getMovieListLiveData().observe(this, Observer { moviesList ->
            if (moviesList != null) {
                adapter = MovieAdapter(this, moviesList)
                gridView.adapter = adapter
                adapter.notifyDataSetChanged()
                if(page > 1){
                    gridView.setSelection(moviesList.size)
                }
            }
        })

        binding.btLoadMore.setOnClickListener {
            viewModel.fetchMovies(++page, true)
        }
    }
}
package com.joabesantana.androidapiconsumer.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.joabesantana.androidapiconsumer.R
import com.joabesantana.androidapiconsumer.databinding.ItemMovieBinding
import com.joabesantana.androidapiconsumer.model.Movie


class MovieAdapter(context: Context, movies: List<Movie>) :
    ArrayAdapter<Movie?>(context, 0, movies) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemListView = convertView
        if (itemListView == null) {
            itemListView = ItemMovieBinding.inflate(LayoutInflater.from(parent.context)).root
        }

        val movie: Movie? = getItem(position)

        val countTextView: TextView = itemListView!!.findViewById(R.id.voteCountTextView)
        val imageView: ImageView = itemListView.findViewById(R.id.imageView)

        countTextView.text = "${movie!!.vote_count.toString()} Ratings"
        Glide.with(context).load(getUrl(movie!!.poster_path)).centerCrop().into(imageView)

        return itemListView
    }

    private fun getUrl(path: String): String {
        return "https://image.tmdb.org/t/p/w500$path"
    }
}
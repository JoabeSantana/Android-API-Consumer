package com.joabesantana.androidapiconsumer.model

import com.joabesantana.androidapiconsumer.model.Movie

data class Results(
    val page: Int = 0,
    val results: MutableList<Movie> = ArrayList()
)
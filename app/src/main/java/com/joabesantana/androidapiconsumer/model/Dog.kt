package com.joabesantana.androidapiconsumer.model

import java.io.Serializable

data class Dog(
    val id: String,
    val breeds: List<Breed>,
    val url: String,
    val width: Int,
    val height: Int,
) : Serializable
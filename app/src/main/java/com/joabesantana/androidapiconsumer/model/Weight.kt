package com.joabesantana.androidapiconsumer.model

import java.io.Serializable

data class Weight(
    val imperial: String,
    val metric: String,
) : Serializable
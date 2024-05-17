package com.joabesantana.androidapiconsumer.model

import java.io.Serializable

data class Breed(
    val weight: Weight,
    val height: Height,
    val id: Int,
    val name: String,
    val bred_for: String,
    val breed_group: String,
    val life_span: String,
    val temperament: String,
    val reference_image_id: String,
) : Serializable
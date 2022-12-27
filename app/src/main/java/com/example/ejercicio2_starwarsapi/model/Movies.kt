package com.example.ejercicio2_starwarsapi.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("results")
    var results: ArrayList<Movie> = arrayListOf()
)

data class Movie (
    @SerializedName("episode_id")
    var id: Long? = null,
    @SerializedName("title")
    var nameMovie: String? = null,
    var image: String? = null
)
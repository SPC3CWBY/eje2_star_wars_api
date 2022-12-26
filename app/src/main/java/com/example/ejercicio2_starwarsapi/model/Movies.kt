package com.example.ejercicio2_starwarsapi.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("episode_id")
    var id: String? = null,
    var nameMovie: String? = null,
    var image: String? = null
)

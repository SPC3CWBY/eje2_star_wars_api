package com.example.ejercicio2_starwarsapi.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("results")
    var results  : ArrayList<Results> = arrayListOf()
)

data class Results (
    var id: String? = null,
    var name: String? = null,
    var height: String? = null,
    @SerializedName("birth_year")
    var birthYear: String? = null,
    var gender: String? = null,
    var homeworld: String? = null,
    var image: String? = null,
    var films: ArrayList<String> = arrayListOf()
)
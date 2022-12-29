package com.example.ejercicio2_starwarsapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Character(
    @SerializedName("results")
    var results: ArrayList<Results> = arrayListOf()
)
@Parcelize
data class Results (
    var id: Long? = null,
    var name: String? = null,
    var height: String? = null,
    @SerializedName("birth_year")
    var birthYear: String? = null,
    var gender: String? = null,
    var homeworld: String? = null,
    var films: ArrayList<String> = arrayListOf()
): Parcelable
package com.example.ejercicio2_starwarsapi.model

import com.google.gson.annotations.SerializedName

data class InfoCharacter(
    var namePlanet: String? = null,
    @SerializedName("rotation_period")
    var rPeriod: String? = null,
    @SerializedName("orbital_period")
    var oPeriod: String? = null,
    var diameter: String? = null,
    var climate: String? = null,
    var terrain: String? = null,
    var population: String? = null
)

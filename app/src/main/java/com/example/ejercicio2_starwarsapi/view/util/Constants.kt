package com.example.ejercicio2_starwarsapi.view.util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    // URL BASE para las API a utilizar | Deben terminar en /
    const val BASE_URL = "https://swapi.dev/api/"
    const val LOGTAG = "LOGS"

    fun getRetrofitCharacter(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
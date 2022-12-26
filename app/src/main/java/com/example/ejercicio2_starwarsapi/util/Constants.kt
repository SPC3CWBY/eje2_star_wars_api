package com.example.ejercicio2_starwarsapi.util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    // URL BASE para las API a utilizar | Deben terminar en /
    const val BASE_URL = "https://swapi.dev/api/"
    const val BASE_IMG_MOVIE = "https://aulavirtual.amaurypm.com/cm2023-1/"
    const val BASE_IMG_CHARACTER = "https://akabab.github.io/starwars-api/api/"
    const val LOGTAG = "LOGS"

    fun getRetrofitCharacter(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun getRetrofitImgMovie():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_IMG_MOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitImgCharacter():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_IMG_CHARACTER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
package com.example.ejercicio2_starwarsapi.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface StarWarsApi {
    //Endpoints

    //SWAPI DEV
    @GET
    fun getCharacters(
        @Url url: String?
    ): Call<Character>

    @GET("planets/{id}")
    fun getInfoCharacter(
        @Path("id") id: String?
    ): Call<InfoCharacter>

    @GET
    fun getMovies(
        @Url url: String?
    ): Call<Movies>

    // akabab.github.io API
    @GET()
    fun getCharacterImg(
        @Url url: String?
    ): Call<CharacterImg>

}


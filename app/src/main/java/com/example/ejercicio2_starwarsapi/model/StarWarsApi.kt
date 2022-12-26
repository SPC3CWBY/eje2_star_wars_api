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
    ): Call<ArrayList<Character>>

    @GET("planets/{id}/")
    fun getInfoCharacter(
        @Path("id") id: String?
    ): Call<InfoCharacter>

    @GET("films/{id}/")
    fun getMovies(
        @Path("id") id: String?
    ): Call<ArrayList<Movies>>

    // akabab.github.io API
    @GET()
    fun getCharacterImg(
        @Url url: String?
    ): Call<CharacterImg>

    //aulavirtual.amaurypm.com API
    @GET("{id}")
    fun getMovieImg(
        @Path("id") id: String?
    ): Call<Movies>
}


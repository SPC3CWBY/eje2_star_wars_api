package com.example.ejercicio2_starwarsapi.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ejercicio2_starwarsapi.databinding.ActivityMoviesBinding
import com.example.ejercicio2_starwarsapi.model.Movies
import com.example.ejercicio2_starwarsapi.model.StarWarsApi
import com.example.ejercicio2_starwarsapi.view.adapters.AdapterMovies
import com.example.ejercicio2_starwarsapi.view.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movies : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Parametros de peliculas donde sale
        val films = intent.getSerializableExtra("films")
        val movie = Movies()

        val call = Constants.getRetrofitCharacter()
            .create(StarWarsApi::class.java)
            .getMovies("films/")
        Log.d(Constants.LOGTAG, "Request: ${call.request()}")

        //
        /*for(film in films){
            movie.results.add(film)
        }*/

        val adapter = AdapterMovies(this, movie)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<Movies>{
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    binding.pb3.visibility = View.GONE
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")


                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    binding.pb3.visibility = View.GONE
                    Toast.makeText(
                        this@Movies,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}
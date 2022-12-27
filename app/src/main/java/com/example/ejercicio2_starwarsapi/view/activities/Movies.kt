package com.example.ejercicio2_starwarsapi.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ejercicio2_starwarsapi.databinding.ActivityMoviesBinding
import com.example.ejercicio2_starwarsapi.model.Movie
import com.example.ejercicio2_starwarsapi.model.MovieImg
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
import retrofit2.create

class Movies : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = Constants.getRetrofitCharacter()
            .create(StarWarsApi::class.java)
            .getMovies("films/")
        Log.d(Constants.LOGTAG, "Request: ${call.request()}")

        val films  = intent.getSerializableExtra("films") as ArrayList<String>
        val filmsID = ArrayList<String>()
        for (x in films){
            println("pepe")
            val tmp = x.split("https",":","/","swapi",".","dev","api","films")
            filmsID.add(tmp.get(12))
        }


        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<Movies>{
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    val datos = Movies()
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    val movImg = MovieImg()
                    for (x in filmsID){
                        var num = -1
                        do {
                            num += 1
                        } while (x != response.body()!!.results.get(num).id.toString())
                        val filmTmp = Movie(x.toLong(), response.body()!!.results.get(num).nameMovie,movImg.imgs.get(x.toInt()-1))
                        datos.results.add(filmTmp)
                    }

                   val adapter = AdapterMovies(this@Movies, datos)
                    binding.lvMovies.adapter = adapter
                    binding.pb3.visibility = View.GONE
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
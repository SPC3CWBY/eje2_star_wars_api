package com.example.ejercicio2_starwarsapi.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ejercicio2_starwarsapi.databinding.ActivityInfoPersonajeBinding
import com.example.ejercicio2_starwarsapi.model.InfoCharacter
import com.example.ejercicio2_starwarsapi.model.StarWarsApi
import com.example.ejercicio2_starwarsapi.view.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoPersonaje : AppCompatActivity() {
    private lateinit var binding: ActivityInfoPersonajeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInfoPersonajeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id")


        val call = Constants.getRetrofitCharacter()
            .create(StarWarsApi::class.java)
            .getInfoCharacter(id)
        Log.d(Constants.LOGTAG, "Request: ${call.request()}")

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<InfoCharacter>{
                override fun onResponse(call: Call<InfoCharacter>, response: Response<InfoCharacter>) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: $response")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")
                    binding.pb2.visibility = View.GONE
                    // DATA
                    with(binding){
                        tvPlanet.text = response.body()?.namePlanet
                        tvRperiod.text = response.body()?.rPeriod
                        tvOperiod.text = response.body()?.oPeriod
                        tvDiameter.text = response.body()?.diameter
                        tvTerrain.text = response.body()?.terrain
                        tvPopulation.text = response.body()?.population
                    }
                }

                override fun onFailure(call: Call<InfoCharacter>, t: Throwable) {
                    binding.pb2.visibility = View.GONE
                    Toast.makeText(
                        this@InfoPersonaje,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    fun OnClick(view: View) {
        val films  = intent.getSerializableExtra("films") as ArrayList<String>

        val intent = Intent(this@InfoPersonaje, Movies::class.java)
        val parameters = Bundle()

        //Movies
        intent.putStringArrayListExtra("films", films)
        intent.putExtras(parameters)
        startActivity(intent)
    }
}
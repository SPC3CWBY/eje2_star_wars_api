package com.example.ejercicio2_starwarsapi.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Toast
import com.example.ejercicio2_starwarsapi.databinding.ActivityMainBinding
import com.example.ejercicio2_starwarsapi.model.Character
import com.example.ejercicio2_starwarsapi.model.Results
import com.example.ejercicio2_starwarsapi.model.StarWarsApi
import com.example.ejercicio2_starwarsapi.view.util.Constants
import com.example.ejercicio2_starwarsapi.view.adapters.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:  ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callCharacter = Constants.getRetrofitCharacter()
            .create(StarWarsApi::class.java)
            .getCharacters("people/")
        Log.d(Constants.LOGTAG, "Request: ${callCharacter.request()}")

        CoroutineScope(Dispatchers.IO).launch {
            callCharacter.enqueue(object : Callback<Character>{
                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")
                    val datos = Character()
                    var i: Long = 0
                    for (x in response.body()!!.results){
                        val characterTmp = Results(i, x.name,
                            x.height,
                            x.birthYear,
                            x.gender,
                            x.homeworld,x.films)
                        datos.results.add(characterTmp)
                        i++
                    }

                    val adapter = Adapter(this@MainActivity, datos)
                    binding.lvMenu.adapter = adapter
                    binding.lvMenu.setOnItemClickListener { parent, view, position, id ->
                        selectedCharacter(datos.results.get(id.toInt()).films, datos.results.get(id.toInt()).homeworld)
                    }
                    binding.pb1.visibility = View.GONE
                }

                override fun onFailure(call: Call<Character>, t: Throwable) {
                    binding.pb1.visibility = View.GONE
                    Toast.makeText(
                        this@MainActivity,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    fun selectedCharacter(films: ArrayList<String>?, planet: String?) {
        val planetID = planet!!
            .split("https",":","/","swapi",".","dev","api","planets")
        val parameters = Bundle()
        parameters.apply{
            //Planeta
            putString("id", planetID.get(12))
            //Movies
        }
        val intent = Intent(this@MainActivity, InfoPersonaje::class.java)
        intent.putStringArrayListExtra("films", films)
        intent.putExtras(parameters)
        startActivity(intent)
    }
}
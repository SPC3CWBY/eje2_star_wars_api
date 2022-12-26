package com.example.ejercicio2_starwarsapi.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.ejercicio2_starwarsapi.databinding.ActivityMainBinding
import com.example.ejercicio2_starwarsapi.model.Character
import com.example.ejercicio2_starwarsapi.model.CharacterImg
import com.example.ejercicio2_starwarsapi.model.StarWarsApi
import com.example.ejercicio2_starwarsapi.util.Constants
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

        CoroutineScope(Dispatchers.IO).launch {
            val callCharacter = Constants.getRetrofitCharacter().create(StarWarsApi::class.java)
                .getCharacters("people/")

            val callCharacterImage = Constants.getRetrofitImgCharacter().create(StarWarsApi::class.java)
                .getCharacterImg("all")

            callCharacter.enqueue(object: Callback<ArrayList<Character>> {
                override fun onResponse(
                    call: Call<ArrayList<Character>>,
                    response: Response<ArrayList<Character>>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")
                    binding.pb1.visibility = View.GONE
                }

                override fun onFailure(call: Call<ArrayList<Character>>, t: Throwable) {
                    binding.pb1.visibility = View.GONE
                }
            })

            callCharacterImage.enqueue(object: Callback<CharacterImg> {
                override fun onResponse(
                    call: Call<CharacterImg>,
                    response: Response<CharacterImg>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")
                    binding.pb1.visibility = View.GONE
                }

                override fun onFailure(call: Call<CharacterImg>, t: Throwable) {
                    binding.pb1.visibility = View.GONE
                }

            })
        }
    }

}
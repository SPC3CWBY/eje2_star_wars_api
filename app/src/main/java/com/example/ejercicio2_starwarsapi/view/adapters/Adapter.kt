package com.example.ejercicio2_starwarsapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.ejercicio2_starwarsapi.databinding.ActivityListCharacterBinding
import com.example.ejercicio2_starwarsapi.model.Character
import com.bumptech.glide.Glide

class Adapter(private val context: Context, val character: Character): BaseAdapter() {
    override fun getCount(): Int = character.results.size


    override fun getItem(p0: Int): Any = character.results[p0]

    override fun getItemId(p0: Int): Long = character.results[p0].id!!

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ActivityListCharacterBinding.inflate(LayoutInflater.from(context))
        with(binding){
            tvNameCharacter.text = character.results[p0].name!!.lowercase()
            tvAltura.text = character.results[p0].height
            tvBirthyear.text = character.results[p0].birthYear
            tvGenre.text = character.results[p0].gender

            // IMG
        }
        return binding.root
    }
}
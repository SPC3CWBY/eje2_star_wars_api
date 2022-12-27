package com.example.ejercicio2_starwarsapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.ejercicio2_starwarsapi.databinding.ActivityListMovieBinding
import com.example.ejercicio2_starwarsapi.model.Movies

class AdapterMovies(private val context: Context, val movie: Movies): BaseAdapter(){
        override fun getCount(): Int = movie.results.size

        override fun getItem(p0: Int): Any = movie.results[p0]

        override fun getItemId(p0: Int): Long = movie.results[p0].id!!

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
                val binding = ActivityListMovieBinding.inflate(LayoutInflater.from(context))

                binding.tvNameMovie.text = movie.results[p0].nameMovie
                // IMG
                Glide.with(context)
                        .load(movie.results[p0].image)
                        .into(binding.ivMovie)

                return binding.root

        }

}
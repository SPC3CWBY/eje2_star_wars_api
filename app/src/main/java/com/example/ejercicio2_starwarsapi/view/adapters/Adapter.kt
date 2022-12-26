package com.example.ejercicio2_starwarsapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2_starwarsapi.databinding.ActivityListCharacterBinding
import com.example.ejercicio2_starwarsapi.model.Character
import com.example.ejercicio2_starwarsapi.view.activities.MainActivity
import com.bumptech.glide.Glide

class Adapter(private val context: Context,
              private val character: Character
               ): RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder (view: ActivityListCharacterBinding): RecyclerView.ViewHolder(view.root) {
        val tvName = view.tvNameCharacter
        val tvAltura= view.tvAltura
        val tvBirthYear = view.tvBirthyear
        val tvGenre = view.tvGenre
        // IMG
        val ivImg = view.ivCharacter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityListCharacterBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = character.results[position].name
        holder.tvAltura.text = character.results[position].name
        holder.tvBirthYear.text = character.results[position].name
        holder.tvGenre.text = character.results[position].name

 /*       Glide.with(context)
            .load(games[position].thumbnail)
            .into(holder.ivThumbnail)
*/
        /*holder.itemView.setOnClickListener {
            if(context is MainActivity) context.selectedCharacter(character.results[position])
        }*/
    }

    override fun getItemCount(): Int = character.results.size
}
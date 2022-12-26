package com.example.ejercicio2_starwarsapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio2_starwarsapi.databinding.ActivityListCharacterBinding
import com.example.ejercicio2_starwarsapi.model.Character
import com.example.ejercicio2_starwarsapi.model.CharacterImg
import com.example.ejercicio2_starwarsapi.view.activities.MainActivity

class Adapter (private val context: Context, private val character: ArrayList<Character>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: ActivityListCharacterBinding) : RecyclerView.ViewHolder(view.root){
        val tvName = view.tvNameCharacter
        val tvAltura = view.tvAltura
        val tvBirthYear = view.tvBirthyear
        val tvGenre = view.tvGenre
        //val ivCharacter = view.ivCharacter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityListCharacterBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = character[position].name
        holder.tvAltura.text = character[position].height
        holder.tvBirthYear.text = character[position].birthYear
        holder.tvGenre.text = character[position].gender
        //IMAGE
        /*Glide.with(context)
            .load(characterImg.image)
            .into(holder.ivCharacter)*/

        //Onclick functions
        holder.itemView.setOnClickListener {
            //
            if(context is MainActivity) context.selectedCharacter(character[position])
        }
    }

    override fun getItemCount(): Int = character.size
}
package com.nourtech.wordpress.pokemon_hub.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nourtech.wordpress.pokemon_hub.R
import com.nourtech.wordpress.pokemon_hub.model.Pokemon


class PokemonRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<PokemonRecyclerViewAdapter.Holder>() {

    private var pokeList: List<Pokemon> = emptyList()

    fun setList(list: List<Pokemon>) {
        pokeList = list
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textView_PokemonName)
        val image: ImageView = itemView.findViewById(R.id.pokemon_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_recyclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.name.text = pokeList[position].name

        Glide.with(context).load(pokeList[position].url)
            .into(holder.image)

    }

    override fun getItemCount(): Int = pokeList.size

    fun getPokemonAt(position: Int): Pokemon{
        return pokeList[position]
    }

}
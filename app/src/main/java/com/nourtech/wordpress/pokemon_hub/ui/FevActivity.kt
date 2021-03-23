package com.nourtech.wordpress.pokemon_hub.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nourtech.wordpress.pokemon_hub.databinding.ActivityFevBinding
import com.nourtech.wordpress.pokemon_hub.ui.adapters.PokemonRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FevActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFevBinding
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // data binding
        binding = ActivityFevBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewModel
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        // setup recycler view
        binding.fevRecyclerView.adapter = PokemonRecyclerViewAdapter(this)
        viewModel.getFevPokemons().observe(this) {
            (binding.fevRecyclerView.adapter as PokemonRecyclerViewAdapter).setList(it)
        }
        setupSwipe()
    }

    private fun setupSwipe() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pokemon = (binding.fevRecyclerView.adapter as PokemonRecyclerViewAdapter)
                    .getPokemonAt(viewHolder.adapterPosition)
                viewModel.deletePokemon(pokemon.id)
                Toast.makeText(baseContext, "pokemon deleted from the fev" , Toast.LENGTH_SHORT).show()
                binding.fevRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.fevRecyclerView)
    }
}
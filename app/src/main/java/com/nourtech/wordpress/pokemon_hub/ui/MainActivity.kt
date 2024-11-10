package com.nourtech.wordpress.pokemon_hub.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nourtech.wordpress.pokemon_hub.databinding.ActivityMainBinding
import com.nourtech.wordpress.pokemon_hub.ui.adapters.PokemonRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewModel
        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]

        // setup recycler view
        binding.recyclerView.adapter = PokemonRecyclerViewAdapter(this)
        viewModel.pokemonList.observe(this) {
            (binding.recyclerView.adapter as PokemonRecyclerViewAdapter).setList(it)
        }
        setupSwipe()

        // no internet toast
        viewModel.internetState.observe(this) {
            if (!it)
                Toast.makeText(baseContext, "no internet ..." ,Toast.LENGTH_SHORT).show()
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun startFevActivity(view: View) {
        startActivity(Intent().setClass(this, FevActivity::class.java))
    }

    private fun setupSwipe() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pokemon = (binding.recyclerView.adapter as PokemonRecyclerViewAdapter)
                    .getPokemonAt(viewHolder.adapterPosition)
                viewModel.addPokemon(pokemon)
                Toast.makeText(baseContext, "pokemon added to the fev" ,Toast.LENGTH_SHORT).show()
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

}
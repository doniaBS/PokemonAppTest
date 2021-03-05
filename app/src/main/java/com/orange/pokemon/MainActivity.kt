package com.orange.pokemon

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.orange.pokemon.adapter.PokemonAdapter
import com.orange.pokemon.data.PokemonDatabase
import com.orange.pokemon.data.PokemonEntity
import com.orange.pokemon.databinding.ActivityMainBinding
import com.orange.pokemon.model.PokemonItem
import com.orange.pokemon.networking.ApiService
import com.orange.pokemon.networking.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pokemonAdapter = PokemonAdapter()
        val service = NetworkClient().getRetrofit().create(ApiService::class.java)
        service.getAllPokemons().enqueue(object : Callback<List<PokemonItem>> {
            override fun onResponse(
                call: Call<List<PokemonItem>>,
                response: Response<List<PokemonItem>>
            ) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: ${response.body()?.get(0)}")
                    val listPokemonItem: List<PokemonItem>? = response.body()
                    val database = PokemonDatabase.getInstance(this@MainActivity)
                    val dao = database.getPokemonDao()
                    GlobalScope.launch(Dispatchers.Main) {
                        dao.insertAll(listPokemonItem!!.map {
                            PokemonEntity(
                                category = it.category,
                                name = it.name,
                                image = it.imageurl
                            )
                        })
                        val entity = dao.getAll()
                        pokemonAdapter.submitList(entity)
                        binding.recycler.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = pokemonAdapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<PokemonItem>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                Toast.makeText(this@MainActivity, "there is an error", Toast.LENGTH_LONG).show()
                val database = PokemonDatabase.getInstance(this@MainActivity)
                val dao = database.getPokemonDao()
                GlobalScope.launch(Dispatchers.Main) {
                    val list = dao.getAll()
                    pokemonAdapter.submitList(list)
                    binding.recycler.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = pokemonAdapter
                    }
                }
            }

        })
    }
}
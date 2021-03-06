package com.orange.pokemon.networking

import com.orange.pokemon.model.PokemonItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("scitbiz/0bfdd96d3ab9ee20c2e572e47c6834c7/raw/pokemons.json")
    fun getAllPokemons(): Call<List<PokemonItem>>

    @POST("user")
    fun createPokemon(@Body pokemon: PokemonItem)


    @GET("pokemon/{id}/{name}")
    fun deletepokemon(@Query("id") id: Int, @Query("name") name: String)


}
package com.orange.pokemon.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert
    fun insertOne(entity: PokemonEntity)

    @Insert
    fun insertAll(entities : List<PokemonEntity>)

    @Delete
    fun deleteOne(entity: PokemonEntity)

    @Query("SELECT * FROM pokemon_table")
    fun getAll() : List<PokemonEntity>
}
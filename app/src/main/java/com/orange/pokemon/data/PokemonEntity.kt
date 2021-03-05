package com.orange.pokemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
   data class PokemonEntity(
   @PrimaryKey(autoGenerate = true)
   val Id: Int = 0,
   val category: String,
   val name: String,
   val image: String,
)
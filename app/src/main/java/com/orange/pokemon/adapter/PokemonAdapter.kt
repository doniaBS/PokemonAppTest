package com.orange.pokemon.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.orange.pokemon.R
import com.orange.pokemon.data.PokemonEntity
import com.orange.pokemon.databinding.PokemonRowLayoutBinding

class PokemonAdapter : ListAdapter<PokemonEntity, PokemonAdapter.PokemonViewholder>(PokemonDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.PokemonViewholder {
        val binding = PokemonRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewholder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewholder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PokemonViewholder(val binding: PokemonRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonEntity) {
            val img = itemView.context
            with(binding) {
                tvFirst.text = pokemon.name
                tvSecond.text = pokemon.category
                Glide.with(img)
                    .load(pokemon.image)
                    .placeholder(R.drawable.ic_android_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .circleCrop()
                    .into(binding.ivLauncher)
            }
        }
    }
}

class PokemonDiffUtils : DiffUtil.ItemCallback<PokemonEntity>() {
    override fun areItemsTheSame(
        oldItem: PokemonEntity,
        newItem: PokemonEntity): Boolean
    {
        TODO("still not implemented yet for that moment ")
    }

    override fun areContentsTheSame(
        oldItem: PokemonEntity,
        newItem: PokemonEntity): Boolean
    {
        TODO("still not implemented yet for that moment")
    }

}
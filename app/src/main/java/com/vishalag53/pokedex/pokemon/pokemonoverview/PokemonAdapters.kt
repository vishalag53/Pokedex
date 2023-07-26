package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.databinding.GridListItemPokemonBinding

class PokemonAdapters(
    private var onClickListener: OnClickListener
) : ListAdapter<PokemonEntity, PokemonAdapters.PokemonViewHolder>(DiffCallback) {


    class PokemonViewHolder(private var binding: GridListItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val gridListItemPokemon: ConstraintLayout = binding.gridListItemPokemon

        fun bind(pokemonEntity: PokemonEntity) {
            binding.pokemonEntity = pokemonEntity
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<PokemonEntity>() {
        override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(GridListItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonEntity = getItem(position)

        val context = holder.itemView.context

        setBkgColor(pokemonEntity.color,holder,context)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(pokemonEntity)
        }
        holder.bind(pokemonEntity)
    }

    private fun setBkgColor(color: Int, holder: PokemonViewHolder, context: Context) {
        holder.gridListItemPokemon.setBackgroundColor(
            ContextCompat.getColor(
                context,
                color
            )
        )
    }

    class OnClickListener(val clickListener : (pokemonEntity: PokemonEntity) -> Unit){
        fun onClick(pokemonEntity: PokemonEntity) = clickListener(pokemonEntity)
    }
}
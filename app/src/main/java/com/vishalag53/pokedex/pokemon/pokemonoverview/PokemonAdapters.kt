package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.databinding.GridListItemPokemonBinding

class PokemonAdapters(
    var onClickListener: OnClickListener
) : ListAdapter<PokemonEntity, PokemonAdapters.PokemonViewHolder>(DiffCallback) {


    class PokemonViewHolder(private var binding: GridListItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val img: ImageView = binding.imgPokemon
        val type1: TextView = binding.txtType1
        val type2: TextView = binding.txtType2
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

        showImg(pokemonEntity.front_default,holder)

        val context = holder.itemView.context
        View.GONE.also {
            holder.type1.visibility = it
            holder.type2.visibility = it
        }

        showType(pokemonEntity.type1,pokemonEntity.type2,holder)

        setBkgColor(pokemonEntity.color,holder,context)

        holder.itemView.setOnClickListener() {
            onClickListener.onClick(pokemonEntity)
        }
        holder.bind(pokemonEntity)
    }

    private fun showImg(frontDefault: String?, holder: PokemonViewHolder) {
        if(frontDefault == null)    View.GONE.also { holder.img.visibility = it }
    }


    private fun showType(
        type1: String?,
        type2: String?,
        holder: PokemonViewHolder
    ) {
        if(type1?.length != 0)  View.VISIBLE.also { holder.type1.visibility = it }
        if(type2?.length != 0)  View.VISIBLE.also { holder.type2.visibility = it }
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
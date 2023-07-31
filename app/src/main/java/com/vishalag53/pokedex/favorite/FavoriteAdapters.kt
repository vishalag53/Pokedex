package com.vishalag53.pokedex.favorite

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
import com.vishalag53.pokedex.databinding.LinearListItemPokemonBinding

class FavoriteAdapters (
    private var onClickListener: OnClickListener
): ListAdapter<PokemonEntity,RecyclerView.ViewHolder>(DiffCallback){

    enum class LayoutType{ GRID, LINEAR }

    private val VIEW_TYPE_GRID = 1
    private val VIEW_TYPE_LINEAR = 2

    var currentLayoutType = LayoutType.GRID

    class GridPokemonViewHolder(private var binding: GridListItemPokemonBinding): RecyclerView.ViewHolder(binding.root){
        val gridListItemPokemon: ConstraintLayout = binding.gridListItemPokemon
        fun bind(pokemonEntity: PokemonEntity) {
            binding.pokemonEntity = pokemonEntity
            binding.executePendingBindings()
        }
    }

    class LinearPokemonViewHolder(private var binding: LinearListItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val linearListItemPokemon: ConstraintLayout = binding.linearListItemPokemon

        fun bind(pokemonEntity: PokemonEntity) {
            binding.pokemonEntity = pokemonEntity
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<PokemonEntity>(){
        override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            VIEW_TYPE_GRID -> {
                val binding = GridListItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GridPokemonViewHolder(binding)
            }
            VIEW_TYPE_LINEAR ->{
                val binding = LinearListItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LinearPokemonViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        val pokemonEntity = getItem(position) as PokemonEntity
        val context = holder.itemView.context
        when (viewType){
            VIEW_TYPE_GRID -> {
                setBkgColor(pokemonEntity.color,holder,viewType,context)
                holder.itemView.setOnClickListener{
                    onClickListener.onClick(pokemonEntity)
                }
                (holder as GridPokemonViewHolder).bind(pokemonEntity)
            }
            VIEW_TYPE_LINEAR -> {
                setBkgColor(pokemonEntity.color,holder,viewType,context)
                holder.itemView.setOnClickListener {
                    onClickListener.onClick(pokemonEntity)
                }
                (holder as LinearPokemonViewHolder).bind(pokemonEntity)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentLayoutType == LayoutType.GRID) VIEW_TYPE_GRID else VIEW_TYPE_LINEAR
    }

    private fun setBkgColor(color: Int, holder: RecyclerView.ViewHolder, viewType: Int, context: Context) {
        when (viewType){
            VIEW_TYPE_GRID -> {
                (holder as GridPokemonViewHolder).gridListItemPokemon.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        color
                    )
                )
            }
            VIEW_TYPE_LINEAR -> {
                (holder as LinearPokemonViewHolder).linearListItemPokemon.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        color
                    )
                )
            }
        }

    }

    class OnClickListener(val clickListener : (pokemonEntity: PokemonEntity) -> Unit){
        fun onClick(pokemonEntity: PokemonEntity) = clickListener(pokemonEntity)
    }

}
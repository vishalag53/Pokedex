package com.vishalag53.pokedex.pokedex.pokedexoverview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.databinding.GridListItemPokedexBinding
import com.vishalag53.pokedex.databinding.LinearListItemPokedexBinding


class PokedexAdapters(
    private var onClickListener: OnClickListener
) : ListAdapter<PokedexEntity, RecyclerView.ViewHolder>(DiffCallback) {

    enum class LayoutType{ GRID, LINEAR }

    private val VIEW_TYPE_GRID = 1
    private val VIEW_TYPE_LINEAR = 2

    var currentLayoutType = LayoutType.GRID

    class GridPokemonViewHolder(private var binding: GridListItemPokedexBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val gridListItemPokemon: ConstraintLayout = binding.gridListItemPokemon

        fun bind(pokedexEntity: PokedexEntity) {
            binding.pokedexEntity = pokedexEntity
            binding.executePendingBindings()
        }
    }

    class LinearPokemonViewHolder(private var binding: LinearListItemPokedexBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val linearListItemPokemon: ConstraintLayout = binding.linearListItemPokemon

        fun bind(pokedexEntity: PokedexEntity) {
            binding.pokedexEntity = pokedexEntity
            binding.executePendingBindings()
        }
    }




    companion object DiffCallback : DiffUtil.ItemCallback<PokedexEntity>() {
        override fun areItemsTheSame(oldItem: PokedexEntity, newItem: PokedexEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokedexEntity, newItem: PokedexEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            VIEW_TYPE_GRID -> {
                val binding = GridListItemPokedexBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GridPokemonViewHolder(binding)
            }
            VIEW_TYPE_LINEAR ->{
                val binding = LinearListItemPokedexBinding.inflate(
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
        val pokedexEntity = getItem(position) as PokedexEntity
        val context = holder.itemView.context
        when (viewType){
            VIEW_TYPE_GRID -> {
                setBkgColor(pokedexEntity.color,holder,viewType,context)
                holder.itemView.setOnClickListener {
                    onClickListener.onClick(pokedexEntity)
                }
                (holder as GridPokemonViewHolder).bind(pokedexEntity)
            }
            VIEW_TYPE_LINEAR ->{
                setBkgColor(pokedexEntity.color,holder,viewType,context)
                holder.itemView.setOnClickListener{
                    onClickListener.onClick(pokedexEntity)
                }
                (holder as LinearPokemonViewHolder).bind(pokedexEntity)
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

    class OnClickListener(val clickListener : (pokedexEntity: PokedexEntity) -> Unit){
        fun onClick(pokedexEntity: PokedexEntity) = clickListener(pokedexEntity)
    }

}
package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.GridListItemPokemonBinding

class PokemonAdapters(
    //var onClickListener: OnClickListener
    val requiredContext: PokemonOverviewFragment,
    var pokemonView: List<PokemonView>,
) : ListAdapter<PokemonView, PokemonAdapters.PokemonViewHolder>(DiffCallback) {


    class PokemonViewHolder(private var binding: GridListItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val img: ImageView = binding.imgPokemon
        val id: TextView = binding.txtNumber
        val name: TextView = binding.txtName
        val type1: TextView = binding.txtType1
        val type2: TextView = binding.txtType2

        fun bind(pokemonView: PokemonView) {
            Log.d("VISHAL", "PokemonViewHolder")
            binding.pokemonView = pokemonView
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<PokemonView>() {
        override fun areItemsTheSame(oldItem: PokemonView, newItem: PokemonView): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokemonView, newItem: PokemonView): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        Log.d("VISHAL", "Pokemon onCreateViewHolder")
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item_pokemon,parent,false)
//        return PokemonViewHolder(view)
        return PokemonViewHolder(GridListItemPokemonBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        Log.d("VISHAL", "Pokemon onBindViewHolder")
//        val imgUrl = pokemonList[position].img
//        val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
//        holder.img.setImageURI(imgUri)
//        holder.id.text = pokemonList[position].id
//        holder.name.text = pokemonList[position].name
//        holder.type1.text = pokemonList[position].type1
//        holder.type2.text = pokemonList[position].type2


        loadImage(pokemonView[position], holder)
        txtNumber(pokemonView[position], holder)
        holder.name.text = pokemonView[position].name
        txtType(pokemonView[position], holder)

        val pokemonView = getItem(position)
        holder.itemView.setOnClickListener() {
            // onClickListener.onClick(pokemonView)
        }
        holder.bind(pokemonView)
    }

    private fun loadImage(
        it: PokemonView,
        holder: PokemonViewHolder,
    ) {
        val imgUrl = it.img
        if (imgUrl == null) View.GONE.also { holder.img.visibility = it }
        else {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(requiredContext)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(holder.img)
        }
    }

    private fun txtNumber(it: PokemonView, holder: PokemonViewHolder): String =
        when (it.id.length) {
            1 -> "#00${it.id}".also { holder.id.text = it }
            2 -> "#0${it.id}".also { holder.id.text = it }
            else -> "#${it.id}".also { holder.id.text = it }
        }

    private fun txtType(
        it: PokemonView,
        holder: PokemonViewHolder,
    ) {
        View.GONE.also { holder.type1.visibility = it }

        if (it.type1?.length != 0) {
            View.VISIBLE.also { holder.type1.visibility = it }
            it.type1.also { holder.type1.text = it }
        }

        View.GONE.also { holder.type2.visibility = it }

        if (it.type2?.length != 0) {
            View.VISIBLE.also { holder.type2.visibility = it }
            it.type2.also { holder.type2.text = it }
        }
    }

//    class OnClickListener(val clickListener : (pokemonView:PokemonView) -> Unit){
//        fun onClick(pokemonView: PokemonView) = clickListener(pokemonView)
//    }
}
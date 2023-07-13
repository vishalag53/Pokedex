package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.GridListItemPokemonBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.network.PokemonRepository
import com.vishalag53.pokedex.response.PokemonInfo

class PokemonOverviewFragment : Fragment() {

    private lateinit var viewModel: PokemonOverviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val binding = DataBindingUtil.inflate<FragmentPokemonOverviewBinding>(inflater,R.layout.fragment_pokemon_overview,container,false)

        val binding = GridListItemPokemonBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)

        val pokemonRepository =  PokemonRepository(pokemonApi)

        viewModel = ViewModelProvider(this,PokemonOverviewViewModelFactory(pokemonRepository))[PokemonOverviewViewModel::class.java]



        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
//            it.results.iterator().forEach {pokemon ->
//                binding.txtName.text = pokemon.name.toString()
//            }

            Log.d("VISHAL","Pokemon")
            val name = it.results[657].name
            binding.txtName.text = name

            Log.d("VISHAL","pokemon $name")
            viewModel.setVariable(name)

            viewModel.getInfo()

        })


        viewModel.pokemonInfo.observe(viewLifecycleOwner,Observer{
            Log.d("VISHAL","PokemonInfo")

            loadImage(it,binding)

            txtNumber(binding, it)

            txtType(it, binding)
        })

        //val manager = GridLayoutManager(activity,3)
        //binding.pokemonList.layoutManager = manager

        return binding.root
    }

    private fun txtType(
        it: PokemonInfo,
        binding: GridListItemPokemonBinding,
    ) {

        View.GONE.also { binding.txtType1.visibility = it }

        if(it.types.isNotEmpty()){
            View.VISIBLE.also { binding.txtType1.visibility = it }
            it.types[0].type.name.also { binding.txtType1.text = it }
        }

        View.GONE.also { binding.txtType2.visibility = it }

        if (it.types.size >= 2) {
            View.VISIBLE.also { binding.txtType2.visibility = it }
            it.types[1].type.name.also { binding.txtType2.text = it }
        }
    }

    private fun txtNumber(
        binding: GridListItemPokemonBinding,
        it: PokemonInfo,
    ) =
        when (it.id.toString().length) {
            1 -> "#00${it.id}".also { binding.txtNumber.text = it }
            2 -> "#0${it.id}".also { binding.txtNumber.text = it }
            else -> "#${it.id}".also { binding.txtNumber.text = it }
        }

    private fun loadImage(
        it: PokemonInfo,
        binding: GridListItemPokemonBinding,
    ) {
        val imgUrl = it.sprites.front_default
        if(imgUrl == null) View.GONE.also { binding.imgPokemon.visibility = it }
        else{
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(this@PokemonOverviewFragment)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.imgPokemon)
        }
    }


}
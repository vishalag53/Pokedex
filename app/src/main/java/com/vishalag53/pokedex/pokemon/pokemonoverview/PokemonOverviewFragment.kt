package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.databinding.FragmentPokemonOverviewBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.network.PokemonRepository

class PokemonOverviewFragment : Fragment() {

    private lateinit var viewModel: PokemonOverviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding = FragmentPokemonOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)

        val pokemonRepository = PokemonRepository(pokemonApi)

        viewModel = ViewModelProvider(
            this,
            PokemonOverviewViewModelFactory(pokemonRepository)
        )[PokemonOverviewViewModel::class.java]

        var pokemonList = mutableListOf<PokemonView>(
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/658.png","1","greninja","water","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png","2","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png","3","greninja","water","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png","4","greninja","water","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png","5","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/658.png","6","greninja","water","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png","7","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/658.png","8","greninja","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/51.png","9","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/351.png","10","greninja","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/41.png","11","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/145.png","12","greninja","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/184.png","13","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/195.png","14","greninja","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/148.png","15","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/175.png","16","greninja","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/110.png","17","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1010.png","18","greninja","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/121.png","19","bulbasaur","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/851.png","20","greninja","grass","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/951.png","21","bulbasaur","grass","poison"),
        )

//        var name = " "

//        PokemonAdapters(pokemonList).also {
//            Log.d("VISHAL", "Pokemon Adapter")
//            binding.pokemonGrid.adapter = it }

        val adapters = PokemonAdapters(this,pokemonList.toMutableList())
        binding.pokemonGrid.visibility = View.VISIBLE
        adapters.notifyDataSetChanged()
        adapters.submitList(pokemonList)
        adapters.also { binding.pokemonGrid.adapter = it }

//
//        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
//            Log.d("VISHAL", "Pokemon")
//            viewModel.getInfo()
//        })
//
//        viewModel.pokemonInfo.observe(viewLifecycleOwner, Observer {
//            Log.d("VISHAL", "PokemonInfo")
//        })
//
////        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
////            Log.d("VISHAL", "Pokemon")
////            it.results.iterator().forEach {pokemon ->
////                name = pokemon.name
////                Log.d("VISHAL", "pokemon $name")
////                viewModel.setName(name)
////                viewModel.getInfo()
////
////            }
////            for (i in 0 until 3) {
////                name = it.results[i].name
////                Log.d("VISHAL", "pokemon $name")
////                viewModel.setName(name)
////                viewModel.getInfo()
////
////                viewModel.pokemonInfo.observe(viewLifecycleOwner, Observer {pInfo ->
////                    Log.d("VISHAL", "PokemonInfo")
////                    val img = pInfo.sprites.front_default
////
////                    val id = txtNumber(pInfo)
////
////                    val type1 = pInfo.types[0].type.name
////                    val type2 = pInfo.types[1].type.name
////
////                    pokemonList.add(PokemonView(img, id, name, type1, type2))
////                })
//
////            }
////        })

        return binding.root
    }
//
//    private fun txtNumber(it: PokemonInfo?): String = when (it?.id.toString().length) {
//        1 -> "#00${it?.id}"
//        2 -> "#0${it?.id}"
//        else -> "#${it?.id}"
//    }
//
////    private fun txtType(
////        it: PokemonInfo,
////        binding: GridListItemPokemonBinding,
////    ) {
////
////        View.GONE.also { binding.txtType1.visibility = it }
////
////        if(it.types.isNotEmpty()){
////            View.VISIBLE.also { binding.txtType1.visibility = it }
////            it.types[0].type.name.also { binding.txtType1.text = it }
////        }
////
////        View.GONE.also { binding.txtType2.visibility = it }
////
////        if (it.types.size >= 2) {
////            View.VISIBLE.also { binding.txtType2.visibility = it }
////            it.types[1].type.name.also { binding.txtType2.text = it }
////        }
////    }
//
////    private fun txtNumber(
////        binding: GridListItemPokemonBinding,
////        it: PokemonInfo,
////    ) =
////        when (it.id.toString().length) {
////            1 -> "#00${it.id}".also { binding.txtNumber.text = it }
////            2 -> "#0${it.id}".also { binding.txtNumber.text = it }
////            else -> "#${it.id}".also { binding.txtNumber.text = it }
////        }
//
////    private fun loadImage(
////        it: PokemonInfo,
////        binding: GridListItemPokemonBinding,
////    ) {
////        val imgUrl = it.sprites.front_default
////        if(imgUrl == null) View.GONE.also { binding.imgPokemon.visibility = it }
////        else{
////            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
////            Glide.with(this@PokemonOverviewFragment)
////                .load(imgUri)
////                .apply(
////                    RequestOptions()
////                        .placeholder(R.drawable.loading_animation)
////                        .error(R.drawable.ic_broken_image)
////                )
////                .into(binding.imgPokemon)
////        }
//    }


}
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
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png","3","greninja","fire","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png","4","greninja","electric","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png","5","bulbasaur","ice","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/658.png","6","greninja","fighting","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png","7","bulbasaur","poison","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/658.png","8","greninja","ground","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/51.png","9","bulbasaur","flying","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/351.png","10","greninja","psychic","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/41.png","11","bulbasaur","bug","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/145.png","12","greninja","rock","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/184.png","13","bulbasaur","ghost","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/195.png","14","greninja","dragon","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/148.png","15","bulbasaur","dark","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/175.png","16","greninja","steel","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/110.png","17","bulbasaur","fairy","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1010.png","18","greninja","Vishal","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/121.png","19","bulbasaur","water","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/851.png","20","greninja","dark","poison"),
            PokemonView("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/951.png","21","bulbasaur","poison","poison"),
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
}
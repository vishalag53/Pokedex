package com.vishalag53.pokedex.ability.abilityoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.MyApplication
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity
import com.vishalag53.pokedex.databinding.FragmentAbilityOverviewBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.repository.AbilityRepository


class AbilityOverviewFragment : Fragment() {


    private lateinit var binding: FragmentAbilityOverviewBinding
    private lateinit var viewModel: AbilityOverviewViewModel
    private lateinit var adapters: AbilityAdapters
    private lateinit var searchView: SearchView
    private lateinit var abilityRepository: AbilityRepository
    private lateinit var abilityListEntities: List<AbilityEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAbilityOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application as MyApplication

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)

        abilityRepository = AbilityRepository(pokemonApi,application.daoDatabaseAbility)

        viewModel = ViewModelProvider(
            this,
            AbilityOverviewViewModelFactory(abilityRepository)
        )[AbilityOverviewViewModel::class.java]

        adapters = AbilityAdapters(AbilityAdapters.OnClickListener{
            viewModel.displayPropertyDetails(it)
        })
        binding.abilityList.adapter = adapters

        viewModel.allAbilityListViews.observe(viewLifecycleOwner){
            abilityListEntities = it
            adapters.submitList(it)
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it){
                this.findNavController().navigate(
                    AbilityOverviewFragmentDirections.actionAbilityOverviewFragmentToAbilityDetailFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        // Search View

        searchView = binding.abilitySearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterAbilityList(newText)
                return true
            }

        })

        return binding.root
    }

    private fun filterAbilityList(nameQuery: String?){
        val filteredList = abilityListEntities.filter { abilityEntity ->
            nameQuery.isNullOrBlank() || abilityEntity.name.contains(
                nameQuery,
                ignoreCase = true
            )
        }

        val noAbilityFoundText = binding.noAbilityFoundText
        noAbilityFoundText.visibility = if(filteredList.isEmpty()) View.VISIBLE else View.GONE
        adapters.submitList(filteredList)
    }

}
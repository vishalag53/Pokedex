package com.vishalag53.pokedex.database.pokemonDatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vishalag53.pokedex.response.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = "pokemon_list_view")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id1: Int = 0,
    val front_default: String?,
    val id: String,
    val name: String,
    val type1: String?,
    val type2: String?,
    val height: String?,
    val weight: String?,
    val hp: String?,
    val attack: String?,
    val defense: String?,
    val special_attack: String?,
    val special_defense: String?,
    val speed: String?,
    val color: Int,
    val total: String?,
    val front_shinny: String?,
    val base_happiness: String?,
    val capture_rate: String?,
    val pokemon_color: String?,
    val egg_groups: @RawValue List<String>,
    val growth_rates: String?,
    val hatch_count: String?,
    val shape: String?,
    // val pokemon_evolution_min_level: List<String>,
    // val pokemon_evolution_pokemon_name: List<String?>,
    // val move: @RawValue List<Move>,
    // val move_name :@RawValue List<Move_Detail>
    ) : Parcelable
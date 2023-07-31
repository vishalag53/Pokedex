package com.vishalag53.pokedex.database.pokedexDatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
@Entity(tableName = "pokedex_list_view")
data class PokedexEntity(
    @PrimaryKey(autoGenerate = true) val id1: Int = 0,
    val frontDefault: String?,
    val id: String,
    val name: String,
    val type1: String?,
    val type2: String?,
    val height: String?,
    val weight: String?,
    val hp: String?,
    val attack: String?,
    val defense: String?,
    val specialAttack: String?,
    val specialDefense: String?,
    val speed: String?,
    val color: Int,
    val total: String?,
    val frontShinny: String?,
    val baseHappiness: String?,
    val captureRate: String?,
    val pokemonColor: String?,
    val eggGroups: String?,
    val growthRates: String?,
    val hatchCount: String?,
    val shape: String?,
    var isFav: Boolean
) : Parcelable
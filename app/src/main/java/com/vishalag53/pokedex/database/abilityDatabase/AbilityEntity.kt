package com.vishalag53.pokedex.database.abilityDatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
@Entity(tableName = "ability_list_view")
data class AbilityEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val inDepthEffect: String,
    val effect: String,
    val pokemonNameList: @RawValue List<String>
): Parcelable
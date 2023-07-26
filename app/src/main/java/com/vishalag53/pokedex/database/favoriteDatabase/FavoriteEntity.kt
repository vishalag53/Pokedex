package com.vishalag53.pokedex.database.favoriteDatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_list_view")
data class FavoriteEntity (
    @PrimaryKey(autoGenerate = true) val id1: Int = 0,
    val front_default: String?,
    val id: String,
    val name: String,
    val type1: String?,
    val type2: String?,
): Parcelable
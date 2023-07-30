package com.vishalag53.pokedex.database.abilityDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AbilityDatabaseDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAbility(abilityListView: AbilityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAbility(abilityListView: List<AbilityEntity>)

    @Query("SELECT * FROM ability_list_view")
    suspend fun getAllAbilityDetailListViews(): List<AbilityEntity>

    @Query("SELECT * from ability_list_view WHERE name = :name")
    suspend fun  getOneAbilityDetailListView(name : String): AbilityEntity?

    @Query("DELETE FROM ability_list_view")
    suspend fun deleteAllAbility()
}
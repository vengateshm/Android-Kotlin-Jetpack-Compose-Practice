package dev.vengateshm.compose_material3.ui_concepts.paging.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BreweryDao {
    @Upsert
    suspend fun upsertAll(breweries: List<BreweryEntity>)

    @Query("SELECT * FROM brewery")
    fun pagingSource(): PagingSource<Int, BreweryEntity>

    @Query("DELETE FROM brewery")
    fun clearAll()
}
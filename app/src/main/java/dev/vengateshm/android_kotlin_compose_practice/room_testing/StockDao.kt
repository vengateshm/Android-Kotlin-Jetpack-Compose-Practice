package dev.vengateshm.android_kotlin_compose_practice.room_testing

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Query("select * from $STOCK_TBL_NAME")
    fun getAllStocks(): Flow<List<Stock>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStock(item: Stock)

    @Delete
    suspend fun removeStock(stock: Stock)

    @Query(
        """
            SELECT *
            FROM stock_tbl
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == symbol
        """
    )
    // %ple% gives Apple
    suspend fun searchStock(query: String): List<Stock>
}
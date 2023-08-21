package dev.vengateshm.android_kotlin_compose_practice.room_db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Query("select * from $STOCK_TBL_NAME")
    fun getAllStocks(): Flow<List<StockEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStock(item: StockEntity)

    @Delete
    suspend fun removeStock(stockEntity: StockEntity)

    @Query(
        """
            SELECT *
            FROM stock_tbl
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == symbol
        """
    )
    // %ple% gives Apple
    suspend fun searchStock(query: String): List<StockEntity>

    @Query(
        """
        SELECT * FROM stock_tbl 
        JOIN stock_fts_tbl ON stock_fts_tbl.name == stock_tbl.name
        WHERE stock_fts_tbl.name MATCH  '*'||:query||'*'
    """
    )
    fun searchStocks(query: String): List<StockEntity>
}
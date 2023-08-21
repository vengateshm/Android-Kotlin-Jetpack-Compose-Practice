package dev.vengateshm.android_kotlin_compose_practice.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

const val STOCK_FTS_TBL_NAME = "STOCK_FTS_TBL"

@Entity(tableName = STOCK_FTS_TBL_NAME)
//@Fts3
@Fts4(contentEntity = StockEntity::class)
data class StockFtsEntity(
    @ColumnInfo(name = "name") val name: String,
)

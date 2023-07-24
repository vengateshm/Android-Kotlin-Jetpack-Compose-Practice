package dev.vengateshm.android_kotlin_compose_practice.room_testing

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val STOCK_TBL_NAME = "STOCK_TBL"

@Entity(tableName = STOCK_TBL_NAME)
data class Stock(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
)

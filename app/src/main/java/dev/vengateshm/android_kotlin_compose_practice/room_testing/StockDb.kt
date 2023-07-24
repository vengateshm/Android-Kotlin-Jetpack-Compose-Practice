package dev.vengateshm.android_kotlin_compose_practice.room_testing

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Stock::class], version = 1, exportSchema = false)
abstract class StockDb : RoomDatabase() {
    abstract fun itemDao(): StockDao
}
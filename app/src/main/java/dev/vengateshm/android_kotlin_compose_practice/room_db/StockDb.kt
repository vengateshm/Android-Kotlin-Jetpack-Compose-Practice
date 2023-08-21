package dev.vengateshm.android_kotlin_compose_practice.room_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StockEntity::class, StockFtsEntity::class], version = 2, exportSchema = false)
abstract class StockDb : RoomDatabase() {
    abstract fun itemDao(): StockDao
}
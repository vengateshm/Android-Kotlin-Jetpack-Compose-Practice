package dev.vengateshm.android_kotlin_compose_practice.room_db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class StockDbCallback :
    RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
    }
}

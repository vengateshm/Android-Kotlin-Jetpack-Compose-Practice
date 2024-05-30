package dev.vengateshm.compose_material3.mvvm_local_db.di

import androidx.room.Room
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val toDoTestModule = module {
    single {
        Room.inMemoryDatabaseBuilder(androidContext(), ToDoDatabase::class.java)
            .allowMainThreadQueries() // Allows database operations on the main thread for testing purposes
            .build()
    }
    single { get<ToDoDatabase>().toDoDao() }
}
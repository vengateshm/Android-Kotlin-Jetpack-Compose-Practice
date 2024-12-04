package dev.vengateshm.compose_material3.testing.mvvm_local_db.di

import androidx.room.Room
import dev.vengateshm.compose_material3.testing.mvvm_local_db.ToDoRepository
import dev.vengateshm.compose_material3.testing.mvvm_local_db.ToDoViewModel
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDoDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoModule = module {
    single {
        val passPhrase = SQLiteDatabase.getBytes("yektercespot".toCharArray())
        val factory = SupportFactory(passPhrase)
        Room.databaseBuilder(androidContext(), ToDoDatabase::class.java, "todo_db_crypted")
            .openHelperFactory(factory)
            .build()
    }
    single { get<ToDoDatabase>().toDoDao() }
    single { ToDoRepository(get()) }
    viewModel { ToDoViewModel(get()) }
}
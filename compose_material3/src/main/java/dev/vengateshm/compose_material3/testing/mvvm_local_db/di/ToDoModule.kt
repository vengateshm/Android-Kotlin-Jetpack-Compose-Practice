package dev.vengateshm.compose_material3.testing.mvvm_local_db.di

import androidx.room.Room
import dev.vengateshm.compose_material3.testing.mvvm_local_db.ToDoRepository
import dev.vengateshm.compose_material3.testing.mvvm_local_db.ToDoViewModel
import dev.vengateshm.compose_material3.testing.mvvm_local_db.room.ToDoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoModule = module {
    single { Room.databaseBuilder(androidContext(), ToDoDatabase::class.java, "todo_db").build() }
    single { get<ToDoDatabase>().toDoDao() }
    single { ToDoRepository(get()) }
    viewModel { ToDoViewModel(get()) }
}
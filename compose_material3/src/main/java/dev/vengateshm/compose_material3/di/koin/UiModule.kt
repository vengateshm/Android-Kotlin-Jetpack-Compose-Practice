package dev.vengateshm.compose_material3.di.koin


import dev.vengateshm.compose_material3.di.koin.ui.KoinDiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { KoinDiViewModel(get()) }
}
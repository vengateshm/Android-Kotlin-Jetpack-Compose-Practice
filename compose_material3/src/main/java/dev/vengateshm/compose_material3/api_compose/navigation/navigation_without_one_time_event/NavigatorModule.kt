package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val navigatorModule = module {
    single<Navigator> { DefaultNavigator(startDest = Dest1.AuthGraph) }
    single<Navigator1> { Navigator1Impl() }
    single<Navigator2> { Navigator2Impl() }
    viewModelOf(::LoginViewModel1)
    viewModelOf(::HomeViewModel1)
    viewModelOf(::DetailViewModel1)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::AddScreenViewModel)
    viewModelOf(::EditScreenViewModel)
}
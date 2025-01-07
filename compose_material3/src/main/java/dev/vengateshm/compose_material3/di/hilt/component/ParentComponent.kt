package dev.vengateshm.compose_material3.di.hilt.component

import dagger.Component
import dev.vengateshm.compose_material3.di.hilt.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ParentComponent {
    fun childComponentFactory(): ChildComponent.Factory
}
package dev.vengateshm.compose_material3.di.hilt.component

import dagger.Subcomponent
import dev.vengateshm.compose_material3.di.hilt.MyContentProvider

@Subcomponent
interface ChildComponent {

    fun inject(myContentProvider: MyContentProvider)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChildComponent
    }

}
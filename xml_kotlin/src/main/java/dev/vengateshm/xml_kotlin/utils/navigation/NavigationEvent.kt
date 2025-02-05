package dev.vengateshm.xml_kotlin.utils.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavOptions

data class NavigationEvent(
    @IdRes val navId: Int,
    val navigationArguments: NavigationArguments? = null,
    val navigationOptions: NavOptions? = null,
) {
    fun argumentsBundle(): Bundle? = navigationArguments?.asBundle()
}

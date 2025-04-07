package dev.vengateshm.xml_kotlin.multi_module

import android.content.Context
import androidx.navigation.NavController
import dev.vengateshm.appcore.comm.CommData
import dev.vengateshm.appcore.comm.CommProcessor

class MainAppFacade(private val context: Context) : CommProcessor {
    override fun doNavigation(
        navController: NavController,
        data: CommData,
    ) {

    }

    override suspend fun processRequest(data: CommData): CommData? {
        return null
    }
}
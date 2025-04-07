package dev.vengateshm.xml_kotlin.multi_module

import android.content.Context
import androidx.navigation.NavController
import dev.vengateshm.appcore.comm.CommData
import dev.vengateshm.appcore.comm.CommType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainFacade(private val context: Context) {
    suspend fun processEvent(
        navController: NavController,
        data: CommData,
    ): CommData? {
        val comm = CommFactory(context).getComm(data)
        return when (data.requestType) {
            CommType.NAVIGATION -> {
                withContext(Dispatchers.Main) {
                    comm.doNavigation(navController, data)
                }
                null
            }

            CommType.GET_DATA -> {
                withContext(Dispatchers.Main) {
                    comm.processRequest(data)
                }
            }
        }
    }
}
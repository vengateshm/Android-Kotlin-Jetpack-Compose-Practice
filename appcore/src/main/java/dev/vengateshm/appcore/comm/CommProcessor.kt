package dev.vengateshm.appcore.comm

import androidx.navigation.NavController

interface CommProcessor {
    fun doNavigation(navController: NavController, data: CommData)
    suspend fun processRequest(data: CommData): CommData?
}
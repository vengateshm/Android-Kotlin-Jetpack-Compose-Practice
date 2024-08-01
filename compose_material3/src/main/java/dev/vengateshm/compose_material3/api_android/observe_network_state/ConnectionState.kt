package dev.vengateshm.compose_material3.api_android.observe_network_state

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

sealed interface ConnectionState {
    data object Available : ConnectionState
    data object Unavailable : ConnectionState
}

fun currentConnectionState(connectivityManager: ConnectivityManager): ConnectionState {
    val network = connectivityManager.activeNetwork

    val isConnected = connectivityManager
        .getNetworkCapabilities(network)
        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    return when (isConnected) {
        true -> ConnectionState.Available
        false -> ConnectionState.Unavailable
    }
}

fun networkCallback(callback: (ConnectionState) -> Unit): ConnectivityManager.NetworkCallback =
    object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(ConnectionState.Available)
        }

        override fun onLost(network: Network) {
            callback(ConnectionState.Unavailable)
        }
    }

@Composable
fun rememberConnectionState(): State<ConnectionState> {
    val context = LocalContext.current
    return produceState(initialValue = context.currentConnectionState) {
        context.observeConnectionState().collect {
            value = it
        }
    }
}

fun Context.observeConnectionState(): Flow<ConnectionState> = callbackFlow {

    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = networkCallback {
        trySend(it)
    }


    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    trySend(currentConnectionState(connectivityManager))

    awaitClose {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

val Context.currentConnectionState: ConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return currentConnectionState(connectivityManager)
    }
package dev.vengateshm.android_kotlin_compose_practice.connectivity_observer

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class NetworkConnectivityObserver(
    private val context: Context,
) : ConnectivityObserver {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingPermission")
    override fun observe(): Flow<ConnectivityObserver.Status> {
        return callbackFlow {
            val callback =
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        launch { send(ConnectivityObserver.Status.Available) }
                    }

                    override fun onLosing(
                        network: Network,
                        maxMsToLive: Int,
                    ) {
                        super.onLosing(network, maxMsToLive)
                        launch { send(ConnectivityObserver.Status.Losing) }
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        launch { send(ConnectivityObserver.Status.Lost) }
                    }

                    override fun onUnavailable() {
                        super.onUnavailable()
                        launch { send(ConnectivityObserver.Status.UnAvailable) }
                    }
                }

            connectivityManager.registerDefaultNetworkCallback(callback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
    }
}

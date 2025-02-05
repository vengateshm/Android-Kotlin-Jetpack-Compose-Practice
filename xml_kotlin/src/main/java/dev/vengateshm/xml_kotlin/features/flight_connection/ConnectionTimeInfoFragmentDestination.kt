package dev.vengateshm.xml_kotlin.features.flight_connection

import android.os.Bundle
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.utils.getParcelableSafely
import dev.vengateshm.xml_kotlin.utils.navigation.NavigationArguments
import dev.vengateshm.xml_kotlin.utils.navigation.NavigationDestination

class ConnectionTimeInfoFragmentDestination(
    connectionTimeData: ConnectionTimeData,
) : NavigationDestination(
    R.id.connectionInfoFragment,
    NavigationArguments.create {
        putParcelable(CONNECTION_TIME_DATA_KEY, connectionTimeData)
    },
) {
    companion object {
        private const val CONNECTION_TIME_DATA_KEY = "connectionTimeDataKey"

        fun getConnectionTimeData(arguments: Bundle?): ConnectionTimeData? =
            arguments?.getParcelableSafely(CONNECTION_TIME_DATA_KEY, ConnectionTimeData::class.java)
    }
}
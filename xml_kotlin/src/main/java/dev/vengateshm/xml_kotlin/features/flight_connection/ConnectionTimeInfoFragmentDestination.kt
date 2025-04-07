package dev.vengateshm.xml_kotlin.features.flight_connection

import android.os.Bundle
import dev.vengateshm.appcore.utility.getParcelableSafely
import dev.vengateshm.commonui.navigation.NavigationArguments
import dev.vengateshm.commonui.navigation.NavigationDestination
import dev.vengateshm.xml_kotlin.R

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
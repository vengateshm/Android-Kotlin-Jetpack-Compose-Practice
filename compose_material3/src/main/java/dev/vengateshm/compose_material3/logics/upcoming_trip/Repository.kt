package dev.vengateshm.compose_material3.logics.upcoming_trip

import java.util.Calendar
import java.util.Date
import java.util.TimeZone

interface WalletReservationRepository {
    suspend fun getWalletReservations(): List<WalletReservation>
}

interface WalletReservationSegmentRepository {
    suspend fun getSegments(recordLocator: String): List<WalletReservationSegment>
}

data class WalletReservation(val recordLocator: String)
data class WalletReservationSegment(val scheduledDepartureDateTimeGMT: Date?)

class FakeWalletReservationRepository : WalletReservationRepository {
    override suspend fun getWalletReservations(): List<WalletReservation> {
        return listOf(
            WalletReservation("ABC123"),
            WalletReservation("DEF456"),
        )
    }
}

class FakeWalletReservationSegmentRepository : WalletReservationSegmentRepository {
    override suspend fun getSegments(recordLocator: String): List<WalletReservationSegment> {
        return when (recordLocator) {
            "ABC123" -> listOf(
                WalletReservationSegment(datePlusDays(5)),
                WalletReservationSegment(datePlusDays(10)),
            )

            "DEF456" -> listOf(
                WalletReservationSegment(datePlusDays(3)),
                WalletReservationSegment(null),
            )

            else -> emptyList()
        }
    }
}

class FakeWalletReservationSegmentRepository1 : WalletReservationSegmentRepository {
    override suspend fun getSegments(recordLocator: String): List<WalletReservationSegment> {
        return when (recordLocator) {
            "ABC123" -> listOf(
                WalletReservationSegment(datePlusDays(55)),
                WalletReservationSegment(datePlusDays(56)),
            )

            "DEF456" -> listOf(
                WalletReservationSegment(datePlusDays(45)),
                WalletReservationSegment(null),
            )

            else -> emptyList()
        }
    }
}

private fun datePlusDays(days: Int): Date {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    calendar.add(Calendar.DAY_OF_MONTH, days)
    return calendar.time
}
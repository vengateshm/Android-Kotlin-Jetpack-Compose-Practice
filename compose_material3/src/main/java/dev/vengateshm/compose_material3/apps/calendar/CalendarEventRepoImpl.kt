package dev.vengateshm.compose_material3.apps.calendar

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.CalendarContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalendarEventRepoImpl(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val eventIdManager: EventIdManager = EventIdManager(context)
) : CalendarEventRepo {

    private val contentResolver = context.contentResolver

    override suspend fun writeEvent(event: CalendarEvent) {
        withContext(dispatcher) {
            val values = ContentValues().apply {
                put(CalendarContract.Events.CALENDAR_ID, eventIdManager.getNextEventId())
                put(CalendarContract.Events.TITLE, event.title)
                put(CalendarContract.Events.DESCRIPTION, event.description)
                put(CalendarContract.Events.DTSTART, event.startTime)
                put(CalendarContract.Events.DTEND, event.endTime)
                put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Kolkata")
                put(CalendarContract.Events.EVENT_LOCATION, "The gym")
            }
            try {
                val uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
                val event = getEventByUri(uri.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Adding via intent to avoid any permission issues
            /*try {
                val intent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.startTime)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.endTime)
                    .putExtra(CalendarContract.Events.TITLE, "Yoga")
                    .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                    .putExtra(
                        CalendarContract.Events.AVAILABILITY,
                        CalendarContract.Events.AVAILABILITY_BUSY
                    )
                    .putExtra(Intent.EXTRA_EMAIL, "vengateshm.13@gmail.com")
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }*/
        }
    }

    override suspend fun deleteEvent(eventId: Long) {
        withContext(dispatcher) {
            val deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventId)
            contentResolver.delete(deleteUri, null, null)
        }
    }

    override suspend fun updateEvent(event: CalendarEvent) {
        withContext(dispatcher) {
            val updateUri =
                ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, event.id!!)
            val values = ContentValues().apply {
                put(CalendarContract.Events.TITLE, event.title)
                put(CalendarContract.Events.DESCRIPTION, event.description)
                put(CalendarContract.Events.DTSTART, event.startTime)
                put(CalendarContract.Events.DTEND, event.endTime)
                // Add other event details as needed
            }
            contentResolver.update(updateUri, values, null, null)
        }
    }

    override suspend fun getEventByDate(date: Long): List<CalendarEvent> {
        return withContext(dispatcher) {
            val projection = arrayOf(
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND
            )
            val selection = "${CalendarContract.Events.DTSTART} >= ?"
            val selectionArgs = arrayOf(date.toString())
            val cursor = contentResolver.query(
                CalendarContract.Events.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null
            )


            cursor.use {
                val events = mutableListOf<CalendarEvent>()
                if (cursor?.moveToFirst() == true) {
                    do {
                        val idColIndex = cursor.getColumnIndex(CalendarContract.Events._ID)
                        val id = cursor.getLong(idColIndex)
                        val titleColIndex = cursor.getColumnIndex(CalendarContract.Events.TITLE)
                        val title = cursor.getString(titleColIndex)
                        val descColIndex =
                            cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION)
                        val description = cursor.getString(descColIndex)
                        val startTimeColIndex =
                            cursor.getColumnIndex(CalendarContract.Events.DTSTART)
                        val startTime = cursor.getLong(startTimeColIndex)
                        val endTimeColIndex = cursor.getColumnIndex(CalendarContract.Events.DTEND)
                        val endTime = cursor.getLong(endTimeColIndex)
                        events.add(CalendarEvent(id, title, description, startTime, endTime))
                    } while (cursor.moveToNext())
                }
                events
            }
        }
    }

    suspend fun getEventByUri(uriCode: String): CalendarEvent? {
        return withContext(dispatcher) {
            val contentResolver = context.contentResolver

            val uri = Uri.parse(uriCode)
            val projection = arrayOf(
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND
            )
            val cursor = contentResolver.query(uri, projection, null, null, null)

            cursor.use {
                if (cursor?.moveToFirst() == true) {
                    val idColIndex = cursor.getColumnIndex(CalendarContract.Events._ID)
                    val id = cursor.getLong(idColIndex)
                    val titleColIndex = cursor.getColumnIndex(CalendarContract.Events.TITLE)
                    val title = cursor.getString(titleColIndex)
                    val descColIndex =
                        cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION)
                    val description = cursor.getString(descColIndex)
                    val startTimeColIndex =
                        cursor.getColumnIndex(CalendarContract.Events.DTSTART)
                    val startTime = cursor.getLong(startTimeColIndex)
                    val endTimeColIndex = cursor.getColumnIndex(CalendarContract.Events.DTEND)
                    val endTime = cursor.getLong(endTimeColIndex)
                    CalendarEvent(id, title, description, startTime, endTime)
                } else {
                    null
                }
            }
        }
    }
}
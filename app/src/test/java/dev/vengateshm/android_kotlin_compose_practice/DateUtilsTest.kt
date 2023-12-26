package dev.vengateshm.android_kotlin_compose_practice

import dev.vengateshm.android_kotlin_compose_practice.utils.toSystemDate
import dev.vengateshm.android_kotlin_compose_practice.utils.toSystemTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtilsTest {
    @Test
    fun `toSystemDate should format date correctly`() {
        val date = Date(1643481600000) // 2022-01-30 00:00:00 UTC
        val formattedDate = date.toSystemDate(DateFormat.FULL, Locale.US)
        val expected = SimpleDateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(date)

        assertEquals(expected, formattedDate)
    }

    @Test
    fun `toSystemTime should format time correctly`() {
        val date = Date(1643481600000) // 2022-01-30 00:00:00 UTC
        val formattedTime = date.toSystemTime(DateFormat.SHORT, Locale.US)
        val expected = SimpleDateFormat.getTimeInstance(DateFormat.SHORT, Locale.US).format(date)

        assertEquals(expected, formattedTime)
    }

    @Test
    fun `string toSystemTime should format time correctly`() {
        val inputTime = "06:30am"
        val formattedTime = inputTime.toSystemTime()
        val expected = SimpleDateFormat.getTimeInstance(DateFormat.SHORT, Locale.US).format(
            SimpleDateFormat("hh:mma", Locale.US).parse(inputTime)!!
        )

        assertEquals(expected, formattedTime)
    }

    @Test
    fun `toSystemTime with custom input format should format time correctly`() {
        val inputTime = "18:45"
        val formattedTime = inputTime.toSystemTime("HH:mm")
        val expected = SimpleDateFormat.getTimeInstance(DateFormat.SHORT, Locale.US).format(
            SimpleDateFormat("HH:mm", Locale.US).parse(inputTime)!!
        )

        assertEquals(expected, formattedTime)
    }

    @Test
    fun `toSystemTime with custom output format should format time correctly`() {
        val inputTime = "03:15 pm"
        val formattedTime = inputTime.toSystemTime("hh:mm a")
        val expected = SimpleDateFormat.getTimeInstance(DateFormat.SHORT, Locale.US).format(
            SimpleDateFormat("hh:mm a", Locale.US).parse(inputTime)!!
        )

        assertEquals(expected, formattedTime)
    }

    @Test
    fun `toSystemTime with invalid input should return null`() {
        val invalidInput = "invalid time"
        val formattedTime = invalidInput.toSystemTime()

        assertNull(formattedTime)
    }

    @Test
    fun `toSystemDate with invalid input should return null`() {
        val invalidInput = "invalid date"
        val formattedTime = invalidInput.toSystemDate()

        assertNull(formattedTime)
    }
}
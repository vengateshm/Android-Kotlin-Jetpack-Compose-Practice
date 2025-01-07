package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(
        canAttendAllMeeting(
            listOf(
                Meeting(7, 10),
                Meeting(2, 4),
            ),
        ),
    )

    println(
        canAttendAllMeeting(
            listOf(
                Meeting(0, 30),
                Meeting(5, 10),
                Meeting(15, 20),
            ),
        ),
    )
}

data class Meeting(
    val startTime: Int,
    val endTime: Int,
)

// T - O(nlogn)
// S - O(1)
fun canAttendAllMeeting(meetings: List<Meeting>): Boolean {
    val sortedMeetings = meetings.sortedBy { it.startTime }
    var lastEnd = sortedMeetings.first().endTime
    for (i in 1 until sortedMeetings.size) {
        if (sortedMeetings[i].startTime < lastEnd)
            return false
        else
            lastEnd = sortedMeetings[i].endTime
    }
    return true
}
package dev.vengateshm.kotlin_practice.design_patterns.observer

typealias Score = Pair<Int, Int>

class Game(
    private val scoreAnnouncer: ScoreAnnouncer,
    private val leadingTeamAnnouncer: LeadingTeamAnnouncer,
) {
    private var score: Score = 0 to 0
        set(value) {
            field = value
            scoreAnnouncer.announceScore(value)
            leadingTeamAnnouncer.announceLeadingTeam(value)
        }

    fun onFirstTeamScores() {
        score = score.copy(first = score.first + 1)
    }

    fun onSecondTeamScores() {
        score = score.copy(second = score.second + 1)
    }
}

class ScoreAnnouncer {
    fun announceScore(score: Score) {
        val (first, second) = score
        println("The score is currently $first to $second.")
    }
}

class LeadingTeamAnnouncer {
    fun announceLeadingTeam(score: Score) {
        val (first, second) = score
        val announcement = when {
            first > second -> "The first team is in the lead."
            second > first -> "The second team is in the lead."
            else -> "The game is all tied up!"
        }
        println(announcement)
    }
}

fun main() {
    val game = Game(ScoreAnnouncer(), LeadingTeamAnnouncer())
    game.onFirstTeamScores()
    game.onSecondTeamScores()
    game.onSecondTeamScores()
}
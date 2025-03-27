package dev.vengateshm.kotlin_practice.design_patterns.observer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import kotlin.properties.Delegates.observable

abstract class Subject {
    private val observers: MutableList<Observer> = mutableListOf()
    fun attach(observer: Observer) {
        observers += observer
    }

    fun detach(observer: Observer) {
        observers -= observer
    }

    protected fun onUpdate() {
        observers.forEach { it.update() }
    }
}

interface Observer {
    fun update()
}

class GameSubject : Subject() {
    var score: Score = 0 to 0
        set(value) {
            field = value
            onUpdate()
        }

    fun onFirstTeamScores() {
        score = score.copy(first = score.first + 1)
    }

    fun onSecondTeamScores() {
        score = score.copy(second = score.second + 1)
    }
}

class ScoreObserver(private val game: GameSubject) : Observer {
    init {
        game.attach(this)
    }

    override fun update() {
        val (first, second) = game.score
        println("The score is currently $first to $second.")
    }
}

class LeadingTeamObserver(private val game: GameSubject) : Observer {
    init {
        game.attach(this)
    }

    override fun update() {
        val (first, second) = game.score
        val announcement = when {
            first > second -> "The first team is in the lead."
            second > first -> "The second team is in the lead."
            else -> "The game is all tied up!"
        }
        println(announcement)
    }
}

// Kotlin way
class GameSubject1(private val observers: List<(Score) -> Unit>) {

    var score: Score by observable(0 to 0) { prop, old, new ->
        observers.forEach { update -> update(new) }
    }

    fun onFirstTeamScores() {
        score = score.copy(first = score.first + 1)
    }

    fun onSecondTeamScores() {
        score = score.copy(second = score.second + 1)
    }
}

class ScoreObserver1() {
    fun update(score: Score) {
        val (first, second) = score
        println("The score is currently $first to $second.")
    }
}

class LeadingTeamObserver1() {
    fun update(score: Score) {
        val (first, second) = score
        val announcement = when {
            first > second -> "The first team is in the lead."
            second > first -> "The second team is in the lead."
            else -> "The game is all tied up!"
        }
        println(announcement)
    }
}

class AsyncGameSubject1() {
    private var _score: MutableStateFlow<Pair<Int, Int>> = MutableStateFlow(0 to 0)
    val score = _score.asStateFlow()

    suspend fun onFirstTeamScores() {
        _score.getAndUpdate { it.copy(first = it.first + 1) }
        yield()
    }

    suspend fun onSecondTeamScores() {
        _score.getAndUpdate { it.copy(second = it.second + 1) }
        yield()
    }
}

fun announceScore(score: Score) {
    val (first, second) = score
    println("The score is currently $first to $second.")
}

fun announceLeadingTeam(score: Score) {
    val (first, second) = score
    val announcement = when {
        first > second -> "The first team is in the lead."
        second > first -> "The second team is in the lead."
        else -> "The game is all tied up!"
    }
    println(announcement)
}

fun main() = runBlocking<Unit> {
    runBlocking {
        val asyncGame = AsyncGameSubject1()
        launch {
            asyncGame.score.collect(::announceScore)
        }
        launch {
            asyncGame.score.collect(::announceLeadingTeam)
        }
        launch {
            asyncGame.onFirstTeamScores()
            asyncGame.onSecondTeamScores()
            asyncGame.onSecondTeamScores()
        }
    }
}

private fun game() {
    val game = GameSubject()
    ScoreObserver(game)
    LeadingTeamObserver(game)
    game.onFirstTeamScores()
    game.onSecondTeamScores()
    game.onSecondTeamScores()
}

private fun game1() {
    val scoreObserver1 = ScoreObserver1()
    val leadingTeamObserver1 = LeadingTeamObserver1()
    val game2 = GameSubject1(listOf(scoreObserver1::update, leadingTeamObserver1::update))
    game2.onFirstTeamScores()
    game2.onSecondTeamScores()
    game2.onSecondTeamScores()
}
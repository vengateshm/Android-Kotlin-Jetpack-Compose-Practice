package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

sealed interface Shot
class Goal1() : Shot
class Miss() : Shot

val rng = Random

fun main() {
  val team = listOf("😀", "🥶", "🥷", "🤡", "🤖", "👾", "👽", "👻", "🎃", "💩")
  val players = flow {
//    repeat(10) {
//      delay(rng.nextInt(500).milliseconds)
//      emit(team.random(rng))
//    }
    team.forEach {
      delay(rng.nextInt(500).milliseconds)
      emit(it)
    }
  }
  val shots = flow {
    repeat(10) {
      delay(rng.nextInt(500).milliseconds)
      emit(if (rng.nextInt(100) < 25) Miss() else Goal1())
    }
  }

  val home = team("🔵Home", 100)
  val away = team("🔴Away", 200)
  val home1 = team1("🔵Home", 100)
  val away1 = team1("🔴Away", 200)
  runBlocking {
    players.zip(shots) { player, shot -> player to shot }.collect { (player, shot) ->
        val result = if (shot is Goal) "it's good!" else "and misses."
        println("⛹️ Player $player takes a shot... $result")
      }

    combine(home, away) { homeScore, awayScore ->
      Score(homeScore, awayScore)
    }.collect { score ->
        println("The score is now:  ${score.team1} to ${score.team2}")
      }

    merge(home1, away1).collect { goal -> println("${goal.team} scored ${goal.points} points!") }
  }
}

fun team(name: String, rngSeed: Int) = flow {
  val rng = Random(rngSeed)
  repeat(rng.nextInt(5, 10)) {
    delay(rng.nextInt(500).milliseconds)
    val randomValue = rng.nextInt(100)
    emit(
      when {
        randomValue < 10 -> ThreePointer(name)
        randomValue < 25 -> FreeThrow(name)
        else -> TwoPointer(name)
      },
    )
  }
}.scan(0) { total, goal -> total + goal.points }

fun team1(name: String, rngSeed: Int) = flow {
  val rng = Random(rngSeed)
  repeat(rng.nextInt(5, 10)) {
    delay(rng.nextInt(500).milliseconds)
    val randomValue = rng.nextInt(100)
    emit(
      when {
        randomValue < 10 -> ThreePointer(name)
        randomValue < 25 -> FreeThrow(name)
        else -> TwoPointer(name)
      },
    )
  }
}

data class Score(val team1: Int = 0, val team2: Int = 0)
sealed class Goal(val team: String, val points: Int)
class FreeThrow(team: String) : Goal(team, 1)
class TwoPointer(team: String) : Goal(team, 2)
class ThreePointer(team: String) : Goal(team, 3)
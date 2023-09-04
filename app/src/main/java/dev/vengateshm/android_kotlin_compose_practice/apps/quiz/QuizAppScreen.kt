package dev.vengateshm.android_kotlin_compose_practice.apps.quiz

sealed class QuizAppScreen(val route: String) {
    object WelcomeScreen : QuizAppScreen("welcome")
    object QuizScreen : QuizAppScreen("questions")
    object ResultScreen : QuizAppScreen("result")
}
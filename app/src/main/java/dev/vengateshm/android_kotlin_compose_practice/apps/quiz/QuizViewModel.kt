package dev.vengateshm.android_kotlin_compose_practice.apps.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var enteredName = ""
    private val questions = QuizUtil.getQuestions()
    var currentQuestion by mutableStateOf(questions[0])
    var currentQuestionIndex = 1
    var correctAnswersCount = 0
    fun onNextClicked() {
        if (currentQuestionIndex <= questions.size) {
            currentQuestionIndex = currentQuestionIndex.plus(1)
            currentQuestion = questions[currentQuestionIndex - 1]
        }
    }

    fun isLastQuestion() = currentQuestionIndex == questions.size
    fun getProgress() = currentQuestionIndex / questions.size.toFloat()

    fun totalCount() = questions.size

    fun reset() {
        enteredName = ""
        correctAnswersCount = 0
        currentQuestionIndex = 1
        currentQuestion = questions[0]
    }
}
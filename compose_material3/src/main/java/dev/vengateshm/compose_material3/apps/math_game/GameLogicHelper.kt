package dev.vengateshm.compose_material3.apps.math_game

import kotlin.random.Random

fun generateQuestion(operationType: OperationType): ArrayList<Any> {
  var number1 = Random.nextInt(0, 100)
  var number2 = Random.nextInt(0, 100)

  var textQuestion: String
  var correctAnswer: Int

  when (operationType) {
    OperationType.ADDITION -> {
      textQuestion = "$number1 + $number2"
      correctAnswer = number1 + number2
    }

    OperationType.SUBTRACTION -> {
      if (number1 > number2) {
        textQuestion = "$number1 - $number2"
        correctAnswer = number1 - number2
      } else {
        textQuestion = "$number2 - $number1"
        correctAnswer = number2 - number1
      }
    }

    OperationType.MULTIPLICATION -> {
      number1 = Random.nextInt(0, 16)
      number2 = Random.nextInt(0, 16)

      textQuestion = "$number1 * $number2"
      correctAnswer = number1 * number2
    }

    OperationType.DIVISION -> {
      if (number1 == 0 || number2 == 0) {
        textQuestion = "0 / 1"
        correctAnswer = 0
      } else if (number1 >= number2) {
        val newBigNumber = number1 - (number1 % number2)
        textQuestion = "$newBigNumber / $number2"
        correctAnswer = newBigNumber / number2
      } else {
        val newBigNumber = number2 - (number2 % number1)
        textQuestion = "$newBigNumber / $number1"
        correctAnswer = newBigNumber / number1
      }
    }
  }

  val questionList = ArrayList<Any>()
  questionList.add(textQuestion)
  questionList.add(correctAnswer)
  return questionList
}
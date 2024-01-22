package dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.Question
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.QuizViewModel
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens.QuestionButtonState.FINISH
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens.QuestionButtonState.GO_TO_NEXT_QUESTION
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens.QuestionButtonState.SUBMIT
import dev.vengateshm.android_kotlin_compose_practice.modifiers.thenWith

@Composable
fun QuestionsScreen(
    viewModel: QuizViewModel,
    onFinishClicked: () -> Unit,
) {
    val question = viewModel.currentQuestion
    var questionButtonState by remember { mutableStateOf(SUBMIT) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var progress by remember { mutableFloatStateOf(viewModel.getProgress()) }
    var evaluateLastQuestion by remember { mutableStateOf(false) }

    LaunchedEffect(question) {
        questionButtonState =
            if (viewModel.isLastQuestion()) {
                FINISH
            } else {
                SUBMIT
            }
        selectedOption = -1
        evaluateLastQuestion = false
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(text = question.question, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier.size(width = 200.dp, height = 100.dp),
            painter = painterResource(id = question.image),
            contentDescription = "${question.correctAnswer} flag image",
        )
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = progress,
            strokeCap = StrokeCap.Round,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OptionButtons(
            question = question,
            spacedBy = 16.dp,
            onOptionSelected = {
                selectedOption = it
                questionButtonState = if (viewModel.isLastQuestion()) FINISH else SUBMIT
            },
            selectedOption = selectedOption,
            questionButtonState = questionButtonState,
            evaluateLastQuestion = evaluateLastQuestion,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            when (questionButtonState) {
                SUBMIT, FINISH -> {
                    if (viewModel.isLastQuestion()) {
                        if (selectedOption == -1) {
                            onFinishClicked()
                        } else {
                            questionButtonState = GO_TO_NEXT_QUESTION
                            if (selectedOption == question.correctAnswer) {
                                viewModel.correctAnswersCount += 1
                            }
                        }
                    } else {
                        questionButtonState = GO_TO_NEXT_QUESTION
                        if (selectedOption == question.correctAnswer) {
                            viewModel.correctAnswersCount += 1
                        }
                    }
                }

                GO_TO_NEXT_QUESTION -> {
                    if (viewModel.isLastQuestion()) {
                        onFinishClicked()
                    } else {
                        viewModel.onNextClicked()
                        progress = viewModel.getProgress()
                    }
                }
            }
        }) {
            Text(text = if (viewModel.isLastQuestion()) FINISH.text else questionButtonState.text)
        }
    }
}

@Composable
fun OptionButtons(
    question: Question,
    spacedBy: Dp,
    onOptionSelected: (Int) -> Unit,
    selectedOption: Int,
    questionButtonState: QuestionButtonState,
    evaluateLastQuestion: Boolean,
) {
    Text(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onOptionSelected(1)
                }
                .thenWith {
                    if (questionButtonState == GO_TO_NEXT_QUESTION || evaluateLastQuestion) {
                        if (1 == selectedOption) {
                            Modifier.background(
                                color = if (1 == question.correctAnswer) Color.Green else Color.Red,
                                shape = RoundedCornerShape(4.dp),
                            )
                        } else {
                            if (1 == question.correctAnswer && selectedOption != -1) {
                                Modifier.background(
                                    color = Color.Green,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            } else {
                                Modifier.border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            }
                        }
                    } else {
                        Modifier.border(
                            width = 1.dp,
                            color = if (1 == selectedOption) MaterialTheme.colors.primary else Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                    }
                },
        text = question.optionOne,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(spacedBy))
    Text(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onOptionSelected(2)
                }
                .thenWith {
                    if (questionButtonState == GO_TO_NEXT_QUESTION || evaluateLastQuestion) {
                        if (2 == selectedOption) {
                            Modifier.background(
                                color = if (2 == question.correctAnswer) Color.Green else Color.Red,
                                shape = RoundedCornerShape(4.dp),
                            )
                        } else {
                            if (2 == question.correctAnswer && selectedOption != -1) {
                                Modifier.background(
                                    color = Color.Green,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            } else {
                                Modifier.border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            }
                        }
                    } else {
                        Modifier.border(
                            width = 1.dp,
                            color = if (2 == selectedOption) MaterialTheme.colors.primary else Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                    }
                },
        text = question.optionTwo,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(spacedBy))
    Text(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onOptionSelected(3)
                }
                .thenWith {
                    if (questionButtonState == GO_TO_NEXT_QUESTION || evaluateLastQuestion) {
                        if (3 == selectedOption) {
                            Modifier.background(
                                color = if (3 == question.correctAnswer) Color.Green else Color.Red,
                                shape = RoundedCornerShape(4.dp),
                            )
                        } else {
                            if (3 == question.correctAnswer && selectedOption != -1) {
                                Modifier.background(
                                    color = Color.Green,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            } else {
                                Modifier.border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            }
                        }
                    } else {
                        Modifier.border(
                            width = 1.dp,
                            color = if (3 == selectedOption) MaterialTheme.colors.primary else Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                    }
                },
        text = question.optionThree,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(spacedBy))
    Text(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onOptionSelected(4)
                }
                .thenWith {
                    if (questionButtonState == GO_TO_NEXT_QUESTION || evaluateLastQuestion) {
                        if (4 == selectedOption) {
                            Modifier.background(
                                color = if (4 == question.correctAnswer) Color.Green else Color.Red,
                                shape = RoundedCornerShape(4.dp),
                            )
                        } else {
                            if (4 == question.correctAnswer && selectedOption != -1) {
                                Modifier.background(
                                    color = Color.Green,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            } else {
                                Modifier.border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(4.dp),
                                )
                            }
                        }
                    } else {
                        Modifier.border(
                            width = 1.dp,
                            color = if (4 == selectedOption) MaterialTheme.colors.primary else Color.LightGray,
                            shape = RoundedCornerShape(4.dp),
                        )
                    }
                },
        text = question.optionFour,
        textAlign = TextAlign.Center,
    )
}

enum class QuestionButtonState(val value: Int, val text: String) {
    SUBMIT(0, "SUBMIT"),
    GO_TO_NEXT_QUESTION(1, "GO TO NEXT QUESTION"),
    FINISH(2, "FINISH"),
}

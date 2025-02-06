package dev.vengateshm.kotlin_practice.ai_ml

import org.jetbrains.kotlinx.dl.api.core.Sequential
import org.jetbrains.kotlinx.dl.api.core.loss.Losses
import org.jetbrains.kotlinx.dl.api.core.metric.Metrics
import org.jetbrains.kotlinx.dl.api.core.optimizer.SGD
import org.jetbrains.kotlinx.dl.dataset.OnHeapDataset
import java.io.File

data class LoanApplication(
    val income: Float,
    val creditScore: Float,
    val loanAmount: Float,
    val employmentType: Float,
    val existingDebt: Float,
)

fun main() {
    val loanApplication = LoanApplication(
        income = 5000f,
        creditScore = 700f,
        loanAmount = 20000f,
        employmentType = 1f,
        existingDebt = 500f,
    )

    val result = predictLoanApproval(loanApplication)
}

fun predictLoanApproval(application: LoanApplication): Any {
    val features = floatArrayOf(
        application.income,
        application.creditScore,
        application.loanAmount,
        application.employmentType,
        application.existingDebt,
    )

    val modelFile = File("kotlin_practice/src/main/resources/loan_data_trained_model")
    val model = Sequential.loadDefaultModelConfiguration(modelFile)
    model.compile(
        optimizer = SGD(0.01f),
        loss = Losses.BINARY_CROSSENTROPY,
        metric = Metrics.ACCURACY,
    )
    model.init()
//    val prediction = model.predict(features)
    val reshapedFeatures = Array(1) { features }
    val predictionDataset = OnHeapDataset.create(
        reshapedFeatures,
        FloatArray(1) { 0f },
    )  // Labels are not needed for prediction
    val prediction = model.predict(dataset = predictionDataset, batchSize = 1)
    return prediction
}
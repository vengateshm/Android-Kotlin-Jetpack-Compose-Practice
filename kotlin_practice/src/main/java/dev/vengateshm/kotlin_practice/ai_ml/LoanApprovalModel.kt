package dev.vengateshm.kotlin_practice.ai_ml

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.jetbrains.kotlinx.dl.api.core.SavingFormat
import org.jetbrains.kotlinx.dl.api.core.Sequential
import org.jetbrains.kotlinx.dl.api.core.activation.Activations
import org.jetbrains.kotlinx.dl.api.core.layer.core.Dense
import org.jetbrains.kotlinx.dl.api.core.layer.core.Input
import org.jetbrains.kotlinx.dl.api.core.loss.Losses
import org.jetbrains.kotlinx.dl.api.core.metric.Metrics
import org.jetbrains.kotlinx.dl.api.core.optimizer.SGD
import org.jetbrains.kotlinx.dl.api.summary.printSummary
import org.jetbrains.kotlinx.dl.dataset.OnHeapDataset
import java.io.File

fun main() {
    val features = mutableListOf<FloatArray>()
    val labels = mutableListOf<Float>()

    val file = File("kotlin_practice/src/main/resources/loan_data.csv")

    csvReader().open(file) {
        readAllAsSequence().drop(1).forEach { row ->
            val income = row[0].toFloat()
            val creditScore = row[1].toFloat()
            val loanAmount = row[2].toFloat()
            val employmentType = row[3].toFloat()
            val existingDebt = row[4].toFloat()
            val approved = row[5].toFloat()

            features.add(
                floatArrayOf(
                    income,
                    creditScore,
                    loanAmount,
                    employmentType,
                    existingDebt,
                ),
            )
            labels.add(approved)
        }
    }

    val featureArray = features.map { it.toList() }.flatten().toFloatArray()
    val labelArray = labels.toFloatArray()
    trainModel(featureArray, labelArray)
}

fun trainModel(features: FloatArray, labels: FloatArray) {
    val reshapedFeatures: Array<FloatArray> = Array(features.size / 5) { i ->
        FloatArray(5) { features[i * 5 + it] }
    }
    val dataset = OnHeapDataset.create(reshapedFeatures, labels)
    val model = Sequential.of(
        Input(5),
        Dense(5, activation = Activations.Relu),
        Dense(1, activation = Activations.Sigmoid),
    )

    model.use {
        it.compile(
            optimizer = SGD(0.01f),
            loss = Losses.BINARY_CROSSENTROPY,
            metric = Metrics.ACCURACY,
        )
        it.printSummary()
        it.fit(dataset, epochs = 100, batchSize = 10)
        val accuracy = it.evaluate(dataset = dataset, batchSize = 100).metrics[Metrics.ACCURACY]
        println("Accuracy: $accuracy")
        it.save(
            File("kotlin_practice/src/main/resources/loan_data_trained_model"),
            savingFormat = SavingFormat.JSON_CONFIG_CUSTOM_VARIABLES,
        )
    }
}

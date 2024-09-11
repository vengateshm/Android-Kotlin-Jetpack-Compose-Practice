package dev.vengateshm.kotlin_practice.design_patterns.pipeline

fun main() {
    val pipeline = OrderProcessingPipeline()
    pipeline.addStage(ValidationStage())
    pipeline.addStage(PaymentStage())
    pipeline.addStage(ShipmentStage())

    val order = POrder(10.0)
    pipeline.execute(order)
}
package dev.vengateshm.kotlin_practice.design_patterns.pipeline

fun main() {
    val pipeline = OrderProcessingPipeline()
    pipeline.addStage(ValidationStage())
    pipeline.addStage(PaymentStage())
    pipeline.addStage(ShipmentStage())

    val order = POrder(10.0)
    pipeline.execute(order)

    val order1 = POrder(0.0)

    executeStages(order1, listOf(validationStage, shipmentStage, paymentStage))
}

fun executeStages(pOrder: POrder, stages: List<OrderProcessStage>): POrder {
    stages.forEach { stage ->
        val order = stage.invoke(pOrder)
        if (order.status == "FAILED" || order.status == "INVALID") {
            println("Exiting pipeline at status: ${order.status}")
            return order
        }
    }
    return pOrder
}
package dev.vengateshm.kotlin_practice.design_patterns.pipeline

class ValidationStage : OrderProcessingStage {
    override fun execute(order: POrder): POrder {
        if (order.amount <= 0) {
            order.status = "INVALID"
            println("Invalid : $order")
        } else {
            order.status = "VALID"
            println("Valid : $order")
        }
        return order
    }
}

typealias OrderProcessStage = (POrder) -> POrder

val validationStage: OrderProcessStage = { order ->
    if (order.amount <= 0) {
        order.status = "INVALID"
        println("Invalid : $order")
    } else {
        order.status = "VALID"
        println("Valid : $order")
    }
    order
}
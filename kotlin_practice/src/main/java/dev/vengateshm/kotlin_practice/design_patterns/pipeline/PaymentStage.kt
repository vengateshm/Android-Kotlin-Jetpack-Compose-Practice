package dev.vengateshm.kotlin_practice.design_patterns.pipeline

class PaymentStage : OrderProcessingStage {
    override fun execute(order: POrder): POrder {
        if ("VALID" == order.status) {
            order.status = "PAID"
            println("Payment done : $order")
        } else {
            order.status = "FAILED"
            println("Payment failed : $order")
        }
        return order
    }
}
package dev.vengateshm.kotlin_practice.design_patterns.pipeline

class ShipmentStage : OrderProcessingStage {
    override fun execute(order: POrder): POrder {
        if ("PAID" == order.status) {
            order.status = "SHIPPED"
            println("Shipment done : $order")
        } else {
            order.status = "FAILED"
            println("Shipment failed : $order")
        }
        return order
    }
}
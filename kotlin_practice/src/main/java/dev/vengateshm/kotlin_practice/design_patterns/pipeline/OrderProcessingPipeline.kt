package dev.vengateshm.kotlin_practice.design_patterns.pipeline

class OrderProcessingPipeline {
    val stages = mutableListOf<OrderProcessingStage>()

    fun addStage(stage: OrderProcessingStage) {
        stages.add(stage)
    }

    fun execute(order: POrder): POrder {
        val mPOrder = order.copy()
        stages.forEach loop@{ stage ->
            val execution = stage.execute(mPOrder)
            if (execution.status == "FAILED" || execution.status == "INVALID") {
                //return@forEach
                return execution
                //return@loop
            }
            println("Stage done $stage")
        }
        return mPOrder
    }
}
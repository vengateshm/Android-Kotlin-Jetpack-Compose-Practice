package dev.vengateshm.kotlin_practice.design_patterns.pipeline

interface OrderProcessingStage {
    fun execute(order: POrder): POrder
}
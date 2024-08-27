package dev.vengateshm.kotlin_practice.inheritance_vs_composition

// Inheritance
open class EVehicle(
    private val acceleration: Double,
) {
    var speed = 0.0
        private set

    fun accelerate() {
        speed += acceleration
    }

    open fun makeEngineSound() = println("No sound")
}

class SparkEV(acceleration: Double) : EVehicle(acceleration) {
    override fun makeEngineSound() {
        println("Spark Spark")
    }
}

// Composition

interface IEVehicle {
    val speed: Double
    fun accelerate()
    fun makeEngineSound()
}

class IEVehicleImpl(private val acceleration: Double) : IEVehicle {
    override var speed = 0.0; private set

    override fun accelerate() {
        speed += acceleration
    }

    override fun makeEngineSound() {
        println("No sound")
    }
}

object Junker : IEVehicle {
    override val speed = 0.0
    override fun accelerate() = Unit
    override fun makeEngineSound() = println("Junk Junk")
}

class ThunderBolt(private val eVehicle: EVehicle) {
    val speed get() = eVehicle.speed
    fun accelerate() = eVehicle.accelerate()
    fun makeEngineSound() = eVehicle.makeEngineSound()
}

class Shocker(var eVehicle: IEVehicle) : IEVehicle {
    override val speed get() = eVehicle.speed
    override fun accelerate() = eVehicle.accelerate()
    override fun makeEngineSound() = eVehicle.makeEngineSound()
}

class ThunderSpark(var eVehicle: IEVehicle) : IEVehicle by eVehicle {
    override fun makeEngineSound() = eVehicle.makeEngineSound()
}

fun main() {
    val sparkEV = SparkEV(10.0)
    drive(sparkEV)
    val thunderBolt = ThunderBolt(sparkEV)
//    drive(composedOla)
    val shocker = Shocker(IEVehicleImpl(10.0))
    drive(shocker)
    shocker.eVehicle = Junker
    drive(shocker)
    val thunderSpark = ThunderSpark(IEVehicleImpl(10.0))
    drive(thunderSpark)
    thunderSpark.eVehicle = Junker
    drive(shocker)
}

fun drive(eVehicle: EVehicle) {
    eVehicle.accelerate()
    eVehicle.makeEngineSound()
    eVehicle.accelerate()
    println(eVehicle.speed)
}

fun drive(eVehicle: IEVehicle) {
    eVehicle.accelerate()
    eVehicle.makeEngineSound()
    eVehicle.accelerate()
    println(eVehicle.speed)
}
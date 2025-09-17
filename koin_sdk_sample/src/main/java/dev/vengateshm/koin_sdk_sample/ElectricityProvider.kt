package dev.vengateshm.koin_sdk_sample

interface ElectricityProvider {
  fun switchOn()
  fun switchOff()
}

class Reliance : ElectricityProvider {
  override fun switchOn() {
    println("Reliance: switchOn")
  }

  override fun switchOff() {
    println("Reliance: switchOff")
  }
}

class TataPower : ElectricityProvider {
  override fun switchOn() {
    println("TataPower: switchOn")
  }

  override fun switchOff() {
    println("TataPower: switchOff")
  }
}

package dev.vengateshm.compose_material3.testing.dsl

class VehicleListActions(
  private val repository: VehicleRepository,
) {
  fun loadVehicles(): List<String> {
    return repository.getVehiclesList()
  }

  fun handleError(): String = "It's probably an issue on our side"

  fun handleEmpty(): String = "No vehicles to show"
}

infix fun VehicleListActions.verify(block: VerifyVehicles.() -> Unit) =
  VerifyVehicles().apply { block() }
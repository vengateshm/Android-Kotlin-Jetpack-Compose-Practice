package dev.vengateshm.compose_material3.testing.dsl

interface VehicleRepository {
  fun getVehiclesList(): List<String>
}

class FakeVehicleRepository : VehicleRepository {
  var vehicles: List<String>? = null
  var shouldThrowError: Boolean = false

  override fun getVehiclesList(): List<String> {
    if (shouldThrowError) throw RuntimeException("Server error")
    return vehicles ?: emptyList()
  }
}

infix fun FakeVehicleRepository.repo(
  block: VehicleListActions.() -> Unit,
) = VehicleListActions(this).apply(block)
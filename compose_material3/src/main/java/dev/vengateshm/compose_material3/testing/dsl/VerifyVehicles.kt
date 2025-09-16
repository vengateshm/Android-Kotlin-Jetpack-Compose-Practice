package dev.vengateshm.compose_material3.testing.dsl

class VerifyVehicles {
  fun showVehicles(vehicles: List<String>) {
    check(vehicles.isNotEmpty()) { "Expected vehicles but got empty" }
  }

  fun showEmpty(message: String) {
    check(message == "No vehicles to show") { "Expected empty state message but got $message" }
  }

  fun showError(message: String) {
    check(message == "It's probably an issue on our side") { "Expected error state message but got $message" }
  }
}
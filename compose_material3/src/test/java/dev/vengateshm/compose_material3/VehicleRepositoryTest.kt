package dev.vengateshm.compose_material3

import dev.vengateshm.compose_material3.testing.dsl.FakeVehicleRepository
import dev.vengateshm.compose_material3.testing.dsl.repo
import dev.vengateshm.compose_material3.testing.dsl.verify
import org.junit.Before
import org.junit.Test

class VehicleRepositoryTest {
  private lateinit var repository: FakeVehicleRepository

  @Before
  fun setup() {
    repository = FakeVehicleRepository()
  }

  @Test
  fun `should return vehicles when available`() {
    with(repository) {
      vehicles = listOf("Mustang", "Camaro")
      repo {
        val vehicles = loadVehicles()
        verify {
          showVehicles(vehicles)
        }
      }
    }
  }

  @Test
  fun `should return empty state when no vehicles available`() {
    with(repository) {
      vehicles = emptyList()
      repo {
        val result = loadVehicles()
        this verify {
          showEmpty(handleEmpty())
        }
      }
    }
  }

  @Test
  fun `should handle error when repository fails`() {
    with(repository) {
      shouldThrowError = true
      repo {
        try {
          loadVehicles()
          assert(false) { "Expected exception" }
        } catch (e: RuntimeException) {
          verify {
            showError(handleError())
          }
        }
      }
    }
  }
}
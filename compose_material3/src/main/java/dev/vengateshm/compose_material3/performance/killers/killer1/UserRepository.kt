package dev.vengateshm.compose_material3.performance.killers.killer1

interface UserRepository {
  suspend fun loadUserData(userId: String): String
  suspend fun loadUserDataFast(userId: String): String
}
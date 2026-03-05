package dev.vengateshm.compose_material3.performance.killers.killer1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FileUserRepository : UserRepository {

  override suspend fun loadUserData(userId: String): String {

    delay(100)

    val fileContent = simulateBlockingFileRead()

    return processUserData(fileContent, userId)
  }

  override suspend fun loadUserDataFast(userId: String): String =
    withContext(Dispatchers.IO) {

      delay(100)

      val fileContent = simulateBlockingFileRead()

      processUserData(fileContent, userId)
    }

  private fun simulateBlockingFileRead(): String {

    val data = StringBuilder()

    repeat(1_000_000) { index ->
      data.append("Line $index: User data\n")
    }

    return data.toString()
  }

  private fun processUserData(
    fileContent: String,
    userId: String,
  ): String {

    var result = ""

    repeat(10_000) {
      result = fileContent.take(100) + userId
    }

    return result
  }
}
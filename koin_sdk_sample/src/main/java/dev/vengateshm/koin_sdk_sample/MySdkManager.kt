package dev.vengateshm.koin_sdk_sample

class MySdkManager(
  private val logger: MySdkLogger,
  private val networkService: MySdkNetworkService,
  private val database: MySdkDatabase,
) {
  fun performAction() {
    logger.log("Starting SDK action...")
    val data = networkService.fetchUserData()
    logger.log("Action completed with data: $data")
    val dbPath = database.getDatabasePath()
    logger.log("Database path: $dbPath")
  }
}
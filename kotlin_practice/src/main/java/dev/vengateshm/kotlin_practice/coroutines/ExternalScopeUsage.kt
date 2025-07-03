package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

val externalScope = CoroutineScope(Dispatchers.IO)

class UserClient {
  suspend fun fetchUser() {

  }
}

class UserDatabase {
  suspend fun saveUser() {

  }
}

class UserRepo(
  private val userClient: UserClient,
  private val userDatabase: UserDatabase,
  private val externalScope: CoroutineScope,
) {
  suspend fun updateUser() = coroutineScope {
    userClient.fetchUser()
    externalScope.launch { userDatabase.saveUser() }
  }
}
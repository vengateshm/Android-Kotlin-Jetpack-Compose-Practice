package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

interface UserActions

suspend fun notifyAnalytics(userActions: List<UserActions>) = coroutineScope {
  userActions.forEach { userAction ->
    launch {
      notifyAnalytics(userAction)
    }
  }
}

// Handle child exceptions using supervisorScope
suspend fun notifyAnalytics1(userActions: List<UserActions>) = supervisorScope {
  userActions.forEach { userAction ->
    launch {
      notifyAnalytics(userAction)
    }
  }
}

suspend fun notifyAnalytics(userActions: UserActions) {
  delay(100)
}
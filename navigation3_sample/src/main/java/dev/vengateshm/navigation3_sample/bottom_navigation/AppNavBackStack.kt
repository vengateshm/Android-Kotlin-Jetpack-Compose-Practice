package dev.vengateshm.navigation3_sample.bottom_navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

class AppNavBackStack<T : NavKey>(private val startKey: T) {
  var bottomTabKey by mutableStateOf(startKey)
    private set

  val backStack = mutableStateListOf<T>(startKey)

  // Key - Tab, Value - Tab screen and other screens called from tab
  private var bottomTabBackStacks: HashMap<T, SnapshotStateList<T>> =
    hashMapOf(startKey to mutableStateListOf(startKey))

  fun switchBottomTab(key: T) {
    if (bottomTabBackStacks[key] == null) {
      println("Initializing stack for tab: $key")
      bottomTabBackStacks[key] = mutableStateListOf(key)
    }
    println("Switching to bottom tab: $key")
    bottomTabKey = key
    updateBackStack()
  }

  fun add(key: T) {
    println("Adding screen $key to tab $bottomTabKey")
    bottomTabBackStacks[bottomTabKey]?.add(key)
    updateBackStack()
  }

  fun removeLast() {
    val currentStack = bottomTabBackStacks[bottomTabKey] ?: return
    if (currentStack.size > 1) {
      val removed = currentStack.removeLastOrNull()
      println("Removed last screen $removed from tab $bottomTabKey")
    } else if (bottomTabKey != startKey) {
      println("Current tab $bottomTabKey has only one screen. Switching back to start tab: $startKey")
      bottomTabKey = startKey
    } else {
      println("Cannot remove last screen from start tab $startKey")
    }
    updateBackStack()
  }

  private fun updateBackStack() {
    backStack.clear()
    val currentTabStack = bottomTabBackStacks[bottomTabKey] ?: emptyList()

    if (bottomTabKey == startKey) {
      backStack.addAll(currentTabStack)
      println("Updated backStack (on start tab): $backStack")
    } else {
      val startTabStack = bottomTabBackStacks[startKey] ?: emptyList()
      backStack.addAll(startTabStack + currentTabStack)
      println("Updated backStack (on non-start tab): $backStack")
    }
  }

  fun replaceStack(vararg keys: T) {
    println("Replacing stack on tab $bottomTabKey with: ${keys.toList()}")
    bottomTabBackStacks[bottomTabKey] = mutableStateListOf(*keys)
    updateBackStack()
  }
}
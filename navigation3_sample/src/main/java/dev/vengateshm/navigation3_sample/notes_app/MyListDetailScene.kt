package dev.vengateshm.navigation3_sample.notes_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND

class MyListDetailScene<T : Any>(
  override val key: Any,
  override val previousEntries: List<NavEntry<T>>,
  val listEntry: NavEntry<T>,
  val detailEntry: NavEntry<T>,
) : Scene<T> {
  override val entries: List<NavEntry<T>> = listOf(listEntry, detailEntry)
  override val content: @Composable (() -> Unit) = {
    Row(modifier = Modifier.fillMaxSize()) {
      Column(modifier = Modifier.weight(0.4f)) {
        listEntry.Content()
      }
      Column(modifier = Modifier.weight(0.6f)) {
        detailEntry.Content()
      }
    }
  }
}

class MyListDetailSceneStrategy<T : Any>(val windowSizeClass: WindowSizeClass) : SceneStrategy<T> {

  override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {

    if (!windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)) {
      return null
    }

    val detailEntry =
      entries.lastOrNull()?.takeIf { it.metadata.containsKey(DETAIL_KEY) } ?: return null
    val listEntry = entries.findLast { it.metadata.containsKey(LIST_KEY) } ?: return null

    val sceneKey = listEntry.contentKey

    return MyListDetailScene(
      key = sceneKey,
      previousEntries = entries.dropLast(1),
      listEntry = listEntry,
      detailEntry = detailEntry,
    )
  }

  companion object {
    const val LIST_KEY = "ListDetailScene-List"
    const val DETAIL_KEY = "ListDetailScene-Detail"

    fun listPane() = mapOf(LIST_KEY to true)
    fun detailPane() = mapOf(DETAIL_KEY to true)
  }
}

@Composable
fun <T : Any> rememberMyListDetailSceneStrategy(): MyListDetailSceneStrategy<T> {
  val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

  return remember(windowSizeClass) {
    MyListDetailSceneStrategy(windowSizeClass)
  }
}
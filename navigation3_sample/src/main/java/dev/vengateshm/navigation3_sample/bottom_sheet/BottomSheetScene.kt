package dev.vengateshm.navigation3_sample.bottom_sheet

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.OverlayScene
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import java.util.UUID

class BottomSheetScene<T : Any>(
  override val key: T,
  override val previousEntries: List<NavEntry<T>>,
  override val overlaidEntries: List<NavEntry<T>>,
  private val entry: NavEntry<T>,
) : OverlayScene<T> {
  override val entries: List<NavEntry<T>> = listOf(entry)
  override val content: @Composable (() -> Unit) = {
    entry.Content()
  }
}

class BottomSheetSceneStrategy<T : Any> : SceneStrategy<T> {
  override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
    val lastEntry = entries.lastOrNull()
    return lastEntry?.metadata?.ifEmpty { null }?.let {
      BottomSheetScene(
        key = lastEntry.contentKey as T,
        previousEntries = entries.dropLast(1),
        overlaidEntries = entries.dropLast(1),
        entry = lastEntry,
      )
    }
  }

  companion object {
    fun bottomSheet(): Map<String, Any> = mapOf(BOTTOM_SHEET_KEY to UUID.randomUUID())
    const val BOTTOM_SHEET_KEY = "bottomSheet"
  }
}
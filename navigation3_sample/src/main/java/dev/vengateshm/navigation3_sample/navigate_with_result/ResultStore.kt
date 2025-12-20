package dev.vengateshm.navigation3_sample.navigate_with_result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

class ResultStore {
  private val results = mutableMapOf<Any, Any?>()

  fun <T> getResult(key: Any): T? = results[key] as? T

  fun <T> setResult(key: Any, value: T) {
    results[key] = value
  }

  fun removeResult(key: Any) {
    results.remove(key)
  }

  companion object {
    val saver = Saver<ResultStore, Map<Any, Any?>>(
      save = { it.results.toMap() },
      restore = {
        ResultStore().apply {
          results.putAll(it)
        }
      },
    )
  }
}

@Composable
fun rememberResultStore() = rememberSaveable(saver = ResultStore.saver) {
  ResultStore()
}
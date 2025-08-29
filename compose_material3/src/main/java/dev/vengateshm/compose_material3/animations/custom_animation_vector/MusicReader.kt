package dev.vengateshm.compose_material3.animations.custom_animation_vector

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

interface MusicReader {
  val waveform: StateFlow<FloatArray>
  val amplitude: StateFlow<Float>
  val frequencies: StateFlow<FloatArray>

  fun loadFile(fileUri: String)
  fun play()
  fun stop()
}

class LoopingMusicReader(private val coroutineScope: CoroutineScope) : MusicReader {
  override val waveform: StateFlow<FloatArray> = MutableStateFlow(floatArrayOf())
  override val amplitude: MutableStateFlow<Float> = MutableStateFlow(0f)
  override val frequencies: StateFlow<FloatArray> = MutableStateFlow(floatArrayOf())

  private val sampleAmplitudes = floatArrayOf(0.1f, 0.3f, 0.5f, 0.7f, 0.9f, 0.7f, 0.5f, 0.3f)

  override fun loadFile(fileUri: String) {}

  override fun play() {
    coroutineScope.launch {
      var index = 0
      while (true) {
        amplitude.value = sampleAmplitudes[index]
        index = (index + 1) % sampleAmplitudes.size
        delay(100)
      }
    }
  }

  override fun stop() {}
}

class RandomMusicReader(private val coroutineScope: CoroutineScope) : MusicReader {
  override val waveform: StateFlow<FloatArray> = MutableStateFlow(floatArrayOf())
  override val amplitude: MutableStateFlow<Float> = MutableStateFlow(0f)
  override val frequencies: StateFlow<FloatArray> = MutableStateFlow(floatArrayOf())

  override fun loadFile(fileUri: String) {}

  override fun play() {
    coroutineScope.launch {
      while (true) {
        amplitude.value = Random.nextFloat() * 0.9f + 0.1f
        delay(50)
      }
    }
  }

  override fun stop() {}
}
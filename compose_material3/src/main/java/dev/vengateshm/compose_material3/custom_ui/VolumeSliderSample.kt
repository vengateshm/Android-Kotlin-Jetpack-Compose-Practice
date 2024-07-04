package dev.vengateshm.compose_material3.custom_ui

import android.content.Context
import android.media.AudioManager
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun VolumeSliderSample(modifier: Modifier = Modifier) {
    VolumeSlider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolumeSlider(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val (sliderValue, setSliderValue) = remember { mutableFloatStateOf(currentVolume.toFloat()) }

    Box(
        modifier = Modifier
            .padding(top = 150.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onBackground)
                .padding(20.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Volume",
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (sliderValue == 0f) Icons.AutoMirrored.Filled.VolumeOff else Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "volume icon",
                    tint = MaterialTheme.colorScheme.background
                )
                Slider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 24.dp),
                    value = sliderValue,
                    valueRange = 0f..1f,
                    onValueChange = { amount ->
                        setSliderValue(amount)
                        val newVolume = (amount * maxVolume).toInt()
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
                    },
                    colors = SliderDefaults.colors(
                        activeTrackColor = MaterialTheme.colorScheme.surface,
                        inactiveTrackColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
                    ),
                    thumb = {
                        SliderDefaults.Thumb(
                            interactionSource = remember { MutableInteractionSource() },
                            thumbSize = DpSize(width = 32.dp, height = 32.dp)
                        )
                    }
                )
            }
        }
    }
}
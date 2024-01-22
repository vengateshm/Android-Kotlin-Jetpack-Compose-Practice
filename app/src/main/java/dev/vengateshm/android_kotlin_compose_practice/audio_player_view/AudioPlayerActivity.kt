package dev.vengateshm.android_kotlin_compose_practice.audio_player_view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PauseCircleFilled
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.vengateshm.android_kotlin_compose_practice.R
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class AudioPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidKotlinComposePracticeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio)
                    AudioPlayer(mediaPlayer)
                }
            }
        }
    }
}

@Composable
fun AudioPlayer(mediaPlayer: MediaPlayer) {
    val viewModel: AudioPlayerViewModel = viewModel()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors = listOf(getRandomColor(), getRandomColor()),
                        ),
                )
                .padding(horizontal = 10.dp),
    ) {
        TopAppBar()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.music),
                contentDescription = "Image Banner",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .sizeIn(maxWidth = 300.dp, maxHeight = 300.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .weight(10f),
            )
            Spacer(modifier = Modifier.height(30.dp))
            SongDetail("Track Name", "Artists")
            Spacer(modifier = Modifier.height(35.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(10f),
            ) {
                PlayerSlider(mediaPlayer)
                Spacer(modifier = Modifier.height(40.dp))
                PlayerControls(modifier = Modifier.padding(vertical = 8.dp), mediaPlayer)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TopAppBar() {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon",
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Add List",
                tint = Color.White,
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Icon",
                tint = Color.White,
            )
        }
    }
}

@Composable
fun SongDetail(
    trackName: String,
    artists: String,
) {
    Text(
        text = trackName,
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )

    // ???
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = artists,
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            color = Color.White,
        )
    }
}

@Composable
fun PlayerSlider(mediaPlayer: MediaPlayer) {
    val viewModel: AudioPlayerViewModel = viewModel()
    val currentMediaTimeInMillis = viewModel.currentMediaTimeInMillis.observeAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        Slider(
            value = currentMediaTimeInMillis.value!!.toFloat(),
            onValueChange = {},
            valueRange = 0f..mediaPlayer.duration.toFloat(),
            colors =
                SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTickColor = Color.White,
                ),
        )

        val currentTimeInMinutes = (currentMediaTimeInMillis.value!! / 1000) / 60.0
        val durationInMinutes = (mediaPlayer.duration / 1000) / 60.0

        val frmt = DecimalFormat()
        frmt.maximumFractionDigits = 2

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${frmt.format(currentTimeInMinutes)} s",
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${frmt.format(durationInMinutes)} s",
                color = Color.White,
            )
        }
    }
}

@Composable
fun PlayerControls(
    modifier: Modifier = Modifier,
    mediaPlayer: MediaPlayer,
    playerButtonSize: Dp = 72.dp,
    sideButtonSize: Dp = 42.dp,
) {
    val viewModel: AudioPlayerViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val isAudioCompleted = viewModel.isAudioCompleted.observeAsState()
    val audioFlag = remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        val buttonModifier =
            Modifier
                .size(sideButtonSize)
                .semantics { role = Role.Button }

        Image(
            imageVector = Icons.Filled.SkipPrevious,
            contentDescription = "Skip Previous",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier,
        )

        Image(
            imageVector = Icons.Filled.Replay10,
            contentDescription = "Replay 10 sec",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier,
        )

        Image(
            imageVector =
                if (isAudioCompleted.value == false) {
                    if (audioFlag.value) {
                        Icons.Filled.PlayCircleFilled
                    } else {
                        Icons.Filled.PauseCircleFilled
                    }
                } else {
                    Icons.Filled.PlayCircleFilled
                },
            contentDescription = "Play / Pause Icon",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier =
                Modifier
                    .size(playerButtonSize)
                    .semantics { role = Role.Button }
                    .clickable {
                        if (audioFlag.value) {
                            mediaPlayer.start()
                            coroutineScope.launch {
                                delay(1000)
                                viewModel.initMediaTimer(mediaPlayer)
                            }
                            audioFlag.value = false
                        } else {
                            audioFlag.value = true
                            mediaPlayer.pause()
                        }
                    },
        )

        Image(
            imageVector = Icons.Filled.Forward10,
            contentDescription = "Forward 10 sec",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier,
        )

        Image(
            imageVector = Icons.Filled.SkipNext,
            contentDescription = "Skip Next",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier,
        )
    }
}

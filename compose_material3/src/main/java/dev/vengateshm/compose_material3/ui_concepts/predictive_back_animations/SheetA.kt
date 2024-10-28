package dev.vengateshm.compose_material3.ui_concepts.predictive_back_animations

import androidx.activity.BackEventCompat
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun SheetA(
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
) {
    var isTextExpanded by remember {
        mutableStateOf(false)
    }
    var backProgress: Float? by remember {
        mutableStateOf(null)
    }
    val shortText =
        remember { "On a summer afternoon, the sun hung low in the sky, casting a golden glow over the sleepy town of Willow Creek. The air was thick and warm, the kind that made everything feel slower, softer, and a little bit magical. Down by the creek, Ella kicked off her shoes and let her bare feet dangle in the cool, rippling water. She had always loved this spot," }
    val longText = remember {
        """On a summer afternoon, the sun hung low in the sky, casting a golden glow over the sleepy town of Willow Creek. The air was thick and warm, the kind that made everything feel slower, softer, and a little bit magical. Down by the creek, Ella kicked off her shoes and let her bare feet dangle in the cool, rippling water. She had always loved this spot, hidden by a wall of wildflowers and shaded by an old willow tree that dipped its branches into the water like fingers testing its depth.

Ella had just settled back against the tree trunk, eyes closed, soaking in the quiet hum of nature, when she heard it—a faint rustle in the bushes behind her. She sat up, looking around, expecting maybe a rabbit or squirrel. But then, stepping out from between the trees, came a boy about her age with tousled dark hair, a smudge of dirt on his cheek, and a mischievous grin. He held up his hands as if caught red-handed.

"Didn’t mean to scare you," he said, his voice soft and warm. "Just didn’t expect anyone else to know about this place."

They exchanged a few words, introductions, little stories, laughter, and then he sat beside her, letting his own feet dip into the water. They talked about everything and nothing—the constellations they barely knew, the mysterious houses around the bend, and dreams of places they’d one day visit. Hours slipped away as the sun started to sink below the horizon, painting the sky in shades of pink and lavender.

As they finally parted ways, Ella felt like she’d discovered something more than just a quiet spot by the creek. And as she walked home, her heart felt light, filled with the thrill of a beginning, and the whispered promise of a summer like no other."""
    }

    var textHeightExpanded by remember {
        mutableStateOf(0.dp)
    }
    var textHeightCollapsed by remember {
        mutableStateOf(0.dp)
    }
    val onBackCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackProgressed(backEvent: BackEventCompat) {
                backProgress = backEvent.progress
            }

            override fun handleOnBackPressed() {
                isTextExpanded = false
                backProgress = null
            }

            override fun handleOnBackCancelled() {
                backProgress = null
            }
        }
    }

    BackHandler(isTextExpanded) {
        isTextExpanded = false
    }

    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher

    DisposableEffect(backPressedDispatcher, isTextExpanded) {
        if (isTextExpanded) {
            backPressedDispatcher.addCallback(onBackCallback)
        }
        onDispose {
            onBackCallback.remove()
        }
    }
    val density = LocalDensity.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Screen A")
        Button(onClick = { onNext() }) {
            Text(text = "Next")
        }
        Text(
            text = if (isTextExpanded) longText else shortText,
            modifier = Modifier
                .onGloballyPositioned {
                    when {
                        isTextExpanded && backProgress == null -> {
                            textHeightExpanded = with(density) {
                                it.size.height.toDp()
                            }
                        }

                        !isTextExpanded -> {
                            textHeightCollapsed = with(density) {
                                it.size.height.toDp()
                            }
                        }
                    }
                }
                .then(
                    if (backProgress != null) {
                        Modifier
                            .heightIn(min = textHeightCollapsed)
                            .height(textHeightExpanded * (1f - (backProgress ?: 0f)))
                    } else Modifier,
                )
                .fillMaxWidth()
                .clickable { isTextExpanded = !isTextExpanded }
                .animateContentSize()
                .padding(16.dp),
        )
    }
}
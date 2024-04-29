package dev.vengateshm.compose_material3.side_effects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProduceStateSample() {
    val post by produceState<Result<Post>?>(initialValue = null) {
        value = getPost()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        post
            ?.onSuccess {

            }
            ?.onFailure {

            }
            ?: CircularProgressIndicator()
    }
}

suspend fun getPost(): Result<Post> {
    return withContext(Dispatchers.IO) {
        delay(3.seconds)
        Result.success(Post(id = 1))
    }
}

data class Post(
    val id: Int,
    val title: String = "",
    val description: String = ""
)
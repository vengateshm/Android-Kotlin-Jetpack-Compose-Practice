package dev.vengateshm.android_kotlin_compose_practice.avoid_duplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun MyListComposable(
    list: List<String>,
    onEvent: (MyListEvent) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        delay(5000L)
        onEvent(MyListEvent.GetMyList)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        list.forEach {
            Text(
                text = it,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent.invoke(MyListEvent.MoveToDetail(data = it))
                        }
                        .padding(16.dp),
            )
        }
    }
}

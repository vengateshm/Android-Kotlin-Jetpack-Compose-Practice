package dev.vengateshm.android_kotlin_compose_practice.localization_app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.R

@Composable
fun ListScreen() {
    // Observe the language change using LocalContext
    val currentContext = LocalContext.current
    val locale by rememberUpdatedState(LocalContext.current.resources.configuration.locales[0])

    val ids = remember { listOf(R.string.apple, R.string.banana, R.string.citrus) }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(ids) { id ->
            Text(
                text = stringResource(id = id),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                style = MaterialTheme.typography.h4,
            )
        }
    }
}

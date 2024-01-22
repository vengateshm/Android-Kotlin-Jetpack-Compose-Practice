package dev.vengateshm.android_kotlin_compose_practice.datastore_protobufs

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dev.vengateshm.android_kotlin_compose_practice.ThemeStore
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

val Context.protoDatastore: DataStore<dev.vengateshm.android_kotlin_compose_practice.ThemeStore> by dataStore(
    fileName = "theme_store.pb",
    serializer = ThemeStoreSerializer,
)

class DatastoreProtobufActivity : ComponentActivity() {
    private lateinit var repo: ProtoDataStoreRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = ProtoDataStoreRepoImpl(protoDatastore)
        setContent {
            AndroidKotlinComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    ThemedScreen(repo)
                }
            }
        }
    }
}

@Composable
fun ThemedScreen(repo: ProtoDataStoreRepo) {
    var isDark by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        repo.isDarkTheme().collectLatest {
            isDark = it
        }
    }

    ThemedComponent(isDark = isDark, onChangeTheme = {
        coroutineScope.launch {
            repo.saveTheme(it)
        }
    })
}

@Composable
fun ThemedComponent(
    isDark: Boolean,
    onChangeTheme: (Boolean) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    color = if (isDark) Color.Black else Color.White,
                ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = if (isDark) "Dark Theme" else "Light Theme",
            color = if (isDark) Color.White else Color.Black,
        )
        Button(
            onClick = {
                onChangeTheme.invoke(isDark.not())
            },
        ) {
            Text(text = "Change Theme")
        }
    }
}

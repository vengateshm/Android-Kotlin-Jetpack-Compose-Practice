package dev.vengateshm.compose_material3.di.koin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.koin.compose.KoinIsolatedContext
import org.koin.compose.koinInject
import org.koin.core.Koin
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class SdkRepository {
  fun getMessage(): String = "Hello from SDK"
}

val sdkModule = module {
  single { SdkRepository() }
}

object MySDKApp {

  val koinSDKApp = koinApplication {
    modules(sdkModule)
  }

  val koin: Koin
    get() = koinSDKApp.koin
}

@Preview
@Composable
fun KoinIsolatedContextSample(modifier: Modifier = Modifier) {
  KoinIsolatedContext(context = MySDKApp.koinSDKApp) {

    MaterialTheme {
      val repo = koinInject<SdkRepository>()

      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        Text(
          text = repo.getMessage(),
          fontSize = 32.sp,
        )
      }
    }
  }
}
package dev.vengateshm.compose_material3.api_compose.navigation.set_result

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dev.vengateshm.compose_material3.api_compose.navigation.RESULT

class A3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            A3()
        }
    }
}

@Composable
fun A3(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as A3Activity
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            activity.setResult(RESULT_OK, Intent().putExtra(RESULT, "Hello from A3"))
            activity.finish()
        }) {
            Text(text = "Set Result")
        }
    }
}

class A3ActivityResultContract : ActivityResultContract<Any?, String?>() {
    override fun createIntent(context: Context, input: Any?): Intent {
        return Intent(context, A3Activity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode != RESULT_OK || intent == null || intent.extras?.containsKey(RESULT) == false) return null
        return intent.getStringExtra(RESULT).takeIf { !it.isNullOrEmpty() }
    }
}
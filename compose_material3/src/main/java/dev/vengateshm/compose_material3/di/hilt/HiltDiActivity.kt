package dev.vengateshm.compose_material3.di.hilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltDiActivity : ComponentActivity() {

    private val tag = "HiltDiActivity"

    @Inject
    lateinit var engine: Engine

    @Inject
    lateinit var paymentGateway: PaymentGateway

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(tag, "Engine: ${engine.name}")
        paymentGateway.pay(1500.0)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {

                }
            }
        }
    }
}
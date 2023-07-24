package dev.vengateshm.compose_material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.components.GenderRadio
import dev.vengateshm.compose_material3.components.LanguagesCheckBox
import dev.vengateshm.compose_material3.components.MyBadge
import dev.vengateshm.compose_material3.components.MyRangeSlider
import dev.vengateshm.compose_material3.components.MySlider
import dev.vengateshm.compose_material3.components.MySwitch
import dev.vengateshm.compose_material3.theme.Material3AppTheme

class ComposeMaterial3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Material3Feed()
                    Column(modifier = Modifier.fillMaxSize()) {
                        GenderRadio(modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(8.dp))
                        LanguagesCheckBox(modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(8.dp))
                        MySwitch()
                        Spacer(modifier = Modifier.height(8.dp))
                        MyBadge()
                        Spacer(modifier = Modifier.height(8.dp))
                        MySlider()
                        Spacer(modifier = Modifier.height(8.dp))
                        MyRangeSlider()
                    }
                }
            }
        }
    }
}
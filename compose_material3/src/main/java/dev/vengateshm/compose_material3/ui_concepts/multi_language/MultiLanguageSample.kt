package dev.vengateshm.compose_material3.ui_concepts.multi_language

import android.app.Activity
import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import dev.vengateshm.compose_material3.R

@Composable
fun MultiLanguageSample(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as? Activity
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Text(text = stringResource(id = R.string.cmaterial3_welcome))
            Button(
                onClick = {
                    changeLanguage(activity, "fr")
                },
            ) {
                Text(text = "Change Language")
            }
        }
    }
}

fun changeLanguage(activity: Activity?, languageCode: String) {
    activity?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.getSystemService(LocaleManager::class.java)?.apply {
                applicationLocales = LocaleList.forLanguageTags(languageCode)
            }
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }
}
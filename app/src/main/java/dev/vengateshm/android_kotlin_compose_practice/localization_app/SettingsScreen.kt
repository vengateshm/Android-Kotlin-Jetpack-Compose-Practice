package dev.vengateshm.android_kotlin_compose_practice.localization_app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.vengateshm.android_kotlin_compose_practice.R
import java.util.Locale

@Composable
fun SettingsScreen() {
    val context = LocalContext.current as? LocaleContextWrapper
    var canShowDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .clickable {
                    canShowDialog = true
                }
                .padding(16.dp),
            text = stringResource(
                id = R.string.change_language
            ),
            style = MaterialTheme.typography.body1
        )
    }
    if (canShowDialog) {
        LanguageChangeDialog(
            onLanguageSelected = { locale ->
                //LocaleContextWrapper.wrap(context!!.baseContext, locale)
                LocaleHelper.setLocale(context!!.baseContext,locale)
            },
            onDismiss = {
                canShowDialog = false
            }
        )
    }
}

@Composable
fun LanguageChangeDialog(
    onLanguageSelected: (Locale) -> Unit,
    onDismiss: () -> Unit
) {
    val locales = remember { listOf(Locale("en"), Locale("fr")) }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(locales) { locale ->
                    Text(
                        text = locale.displayLanguage,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onLanguageSelected(locale)
                                onDismiss()
                            }
                            .padding(16.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}
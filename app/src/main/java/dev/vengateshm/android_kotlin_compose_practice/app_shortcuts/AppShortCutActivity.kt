package dev.vengateshm.android_kotlin_compose_practice.app_shortcuts

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.getSystemService
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import dev.vengateshm.android_kotlin_compose_practice.R

class AppShortCutActivity : ComponentActivity() {

    private val viewModel: AppShortcutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleIntent(intent)

        addDynamicShortcut()
        addPinnedShortcut()

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when (viewModel.shortcutType) {
                        ShortcutType.STATIC -> Text(text = "Static shortcut clicked")
                        ShortcutType.DYNAMIC -> Text(text = "Dynamic shortcut clicked")
                        ShortcutType.PINNED -> Text(text = "Pinned shortcut clicked")
                        null -> Unit
                    }
                }
            }
        }
    }

    private fun addDynamicShortcut() {
        val shortcutInfoCompat = ShortcutInfoCompat.Builder(this, "dynamic_shortcut")
            .setShortLabel("Dynamic")
            .setLongLabel("Dynamic Shortcut")
            .setIcon(IconCompat.createWithResource(this, R.drawable.check_circle))
            .setIntent(Intent(applicationContext, AppShortCutActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                putExtra("shortcut_id", "dynamic_shortcut")
            })
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(applicationContext, shortcutInfoCompat)
    }

    private fun addPinnedShortcut() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val shortcutManager = getSystemService<ShortcutManager>()!!
        if (shortcutManager.isRequestPinShortcutSupported) {
            val shortcutInfo = ShortcutInfo.Builder(this, "pinned_shortcut")
                .setShortLabel("Pinned")
                .setLongLabel("Pinned Shortcut")
                .setIcon(Icon.createWithResource(this, R.drawable.cmaterial3_chat_app_mic))
                .setIntent(Intent(applicationContext, AppShortCutActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra("shortcut_id", "pinned_shortcut")
                })
                .build()

            val callbackIntent =
                shortcutManager.createShortcutResultIntent(shortcutInfo)
            val pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                0,
                callbackIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            shortcutManager.requestPinShortcut(shortcutInfo, pendingIntent.intentSender)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.let {
            when (intent.getStringExtra("shortcut_id")) {
                "static_shortcut" -> viewModel.onShortCutClicked(ShortcutType.STATIC)
                "dynamic_shortcut" -> viewModel.onShortCutClicked(ShortcutType.DYNAMIC)
                "pinned_shortcut" -> viewModel.onShortCutClicked(ShortcutType.PINNED)
            }
        }
    }
}

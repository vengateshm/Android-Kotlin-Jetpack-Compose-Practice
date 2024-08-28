package dev.vengateshm.glance_app_widget.widget

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.gson.Gson
import dagger.hilt.EntryPoints
import dev.vengateshm.appcore.PreferenceProviderEntryPoint
import dev.vengateshm.glance_app_widget.R
import dev.vengateshm.glance_app_widget.models.LiveMatchesResponse
import dev.vengateshm.glance_app_widget.service.LiveScoreService
import dev.vengateshm.glance_app_widget.utils.LIVE_MATCHES_RESPONSE_KEY
import dev.vengateshm.glance_app_widget.utils.startServiceInForeground
import dev.vengateshm.glance_app_widget.workers.LiveScoreWidgetCoroutineWorker
import kotlinx.coroutines.flow.first

class LiveCricketScoreWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            var cachedResponse by remember { mutableStateOf<LiveMatchesResponse?>(null) }
            LaunchedEffect(key1 = true) {
                try {
                    val preferenceProvider = EntryPoints.get(
                        context.applicationContext,
                        PreferenceProviderEntryPoint::class.java
                    ).preferenceProvider()
                    val prefs = preferenceProvider.prefsDatastore().data.first()
                    val respStr = prefs[LIVE_MATCHES_RESPONSE_KEY]
                    if (respStr.isNullOrEmpty()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            OneTimeWorkRequestBuilder<LiveScoreWidgetCoroutineWorker>().build()
                                .also {
                                    WorkManager.getInstance(context).enqueue(it)
                                }
                        } else {
                            context.startServiceInForeground(
                                Intent(
                                    context,
                                    LiveScoreService::class.java
                                )
                            )
                        }
                    }
                    cachedResponse = Gson().fromJson(respStr, LiveMatchesResponse::class.java)
                } catch (e: Exception) {
                    Log.d("LiveCricketScoreWidget", e.toString())
                }
            }
            Column(
                modifier = GlanceModifier.fillMaxSize()
                    .background(imageProvider = ImageProvider(R.drawable.widget_initial_layout_bg))
                    .appWidgetBackground().padding(16.dp)
            ) {
                if (cachedResponse != null) {
                    cachedResponse!!.stages.first().let {
                        if (it.events.isEmpty().not()) {
                            it.events[0].let { event ->
                                Text(text = event.epsL)
                                Spacer(
                                    modifier = GlanceModifier.height(8.dp)
                                )
                                Text(
                                    text = event.eCo,
                                    style = TextStyle(
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                    }
                } else {
                    Text(text = "Live score not available")
                }
                Spacer(
                    modifier = GlanceModifier.height(16.dp)
                )
                Row(
                    modifier = GlanceModifier.fillMaxWidth()
                        .clickable(actionRunCallback<RefreshScoreClickAction>()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Refresh Score",
                        modifier = GlanceModifier.wrapContentSize(),
                        style = TextStyle(
                            color = ColorProvider(Color(0XFF777777)),
                            fontSize = 18.sp
                        )
                    )
                    Image(
                        modifier = GlanceModifier.size(24.dp),
                        provider = ImageProvider(R.drawable.ic_outline_refresh_24_black),
                        contentDescription = "Refresh Icon"
                    )
                }
            }
        }
    }
}

class RefreshScoreClickAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            OneTimeWorkRequestBuilder<LiveScoreWidgetCoroutineWorker>().build()
                .also {
                    WorkManager.getInstance(context).enqueue(it)
                }
        } else {
            context.startServiceInForeground(
                Intent(
                    context,
                    LiveScoreService::class.java
                )
            )
        }
    }
}

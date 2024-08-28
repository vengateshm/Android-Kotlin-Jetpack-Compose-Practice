package dev.vengateshm.glance_app_widget.widget

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
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
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.gson.Gson
import dagger.hilt.EntryPoints
import dev.vengateshm.appcore.PreferenceProviderEntryPoint
import dev.vengateshm.glance_app_widget.R
import dev.vengateshm.glance_app_widget.models.SummaryResponse
import dev.vengateshm.glance_app_widget.service.COVID19DataService
import dev.vengateshm.glance_app_widget.utils.SUMMARY_RESPONSE_KEY
import dev.vengateshm.glance_app_widget.utils.startServiceInForeground
import dev.vengateshm.glance_app_widget.workers.COVIDSummaryUpdateWorker
import kotlinx.coroutines.flow.first

class COVID19Widget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            var cachedResponse by remember { mutableStateOf<SummaryResponse?>(null) }
            LaunchedEffect(Unit) {
                try {
                    val preferenceProvider = EntryPoints.get(
                        context.applicationContext,
                        PreferenceProviderEntryPoint::class.java
                    ).preferenceProvider()
                    val prefs = preferenceProvider.prefsDatastore().data.first()
                    val respStr = prefs[SUMMARY_RESPONSE_KEY]
                    if (respStr.isNullOrEmpty()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            OneTimeWorkRequestBuilder<COVIDSummaryUpdateWorker>().build()
                                .also {
                                    WorkManager.getInstance(context).enqueue(it)
                                }
                        } else {
                            context.startServiceInForeground(
                                Intent(
                                    context,
                                    COVID19DataService::class.java
                                )
                            )
                        }
                    }
                    cachedResponse = Gson().fromJson(respStr, SummaryResponse::class.java)
                } catch (e: Exception) {
                    Log.d("COVID19Widget", e.toString())
                }
            }

            val confirmedDef = "Confirmed Cases - NA"
            val deathsDef = "Deaths - NA"
            val recoveredDef = "Recovered - NA"

            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(imageProvider = ImageProvider(R.drawable.summary_widget_bg))
                    .appWidgetBackground()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = GlanceModifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = GlanceModifier.size(24.dp),
                        provider = ImageProvider(R.drawable.ic_covid_19),
                        contentDescription = "Refresh Icon"
                    )
                    Spacer(
                        modifier = GlanceModifier
                            .height(8.dp)
                    )
                    Text(
                        text = "COVID19 Global Summary",
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .defaultWeight(),
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontSize = 18.sp
                        )
                    )
                    Image(
                        modifier = GlanceModifier.size(24.dp)
                            .clickable(actionRunCallback<RefreshButtonClickAction>()),
                        provider = ImageProvider(R.drawable.ic_outline_refresh_24),
                        contentDescription = "Refresh Icon"
                    )
                }
                Spacer(
                    modifier = GlanceModifier
                        .height(8.dp)
                )
                Summary(
                    title = "New",
                    confirmed = cachedResponse?.let { "Confirmed Cases - ${cachedResponse!!.global.newConfirmed}" }
                        ?: confirmedDef,
                    deaths = cachedResponse?.let { "Deaths - ${cachedResponse!!.global.newDeaths}" }
                        ?: deathsDef,
                    recovered = cachedResponse?.let { "Recovered - ${cachedResponse!!.global.newRecovered}" }
                        ?: recoveredDef
                )
                Row(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0XFFFFFFFF))
                ) {
                }
                Summary(
                    title = "Total",
                    confirmed = cachedResponse?.let { "Confirmed Cases - ${cachedResponse!!.global.totalConfirmed}" }
                        ?: confirmedDef,
                    deaths = cachedResponse?.let { "Deaths - ${cachedResponse!!.global.totalDeaths}" }
                        ?: deathsDef,
                    recovered = cachedResponse?.let { "Recovered - ${cachedResponse!!.global.totalRecovered}" }
                        ?: recoveredDef
                )
            }
        }
    }
}

@Composable
fun Summary(title: String, confirmed: String, deaths: String, recovered: String) {
    Column {
        Text(
            text = title,
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(8.dp),
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 18.sp
            )
        )
        Spacer(
            modifier = GlanceModifier
                .height(8.dp)
        )
        Text(
            text = confirmed,
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(8.dp),
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 18.sp
            )
        )
        Spacer(
            modifier = GlanceModifier
                .height(8.dp)
        )
        Text(
            text = deaths,
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(8.dp),
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 18.sp
            )
        )
        Text(
            text = recovered,
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(8.dp),
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 18.sp
            )
        )
    }
}

class RefreshButtonClickAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        context.startServiceInForeground(Intent(context, COVID19DataService::class.java))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            OneTimeWorkRequestBuilder<COVIDSummaryUpdateWorker>().build()
                .also {
                    WorkManager.getInstance(context).enqueue(it)
                }
        } else {
            context.startServiceInForeground(
                Intent(
                    context,
                    COVID19DataService::class.java
                )
            )
        }
    }
}

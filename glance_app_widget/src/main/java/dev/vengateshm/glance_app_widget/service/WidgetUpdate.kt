package dev.vengateshm.glance_app_widget.service

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import dagger.hilt.EntryPoints
import dev.vengateshm.common_lib.PreferenceProviderEntryPoint
import dev.vengateshm.glance_app_widget.network.COVIDApiService
import dev.vengateshm.glance_app_widget.network.LiveScoreApiService
import dev.vengateshm.glance_app_widget.receiver.COVID19WidgetReceiver
import dev.vengateshm.glance_app_widget.receiver.LiveCricketScoreWidgetReceiver
import dev.vengateshm.glance_app_widget.utils.LIVE_MATCHES_RESPONSE_KEY
import dev.vengateshm.glance_app_widget.utils.SUMMARY_RESPONSE_KEY

suspend fun updateLiveScoreWidget(context: Context) {
    val preferenceProvider =
        EntryPoints.get(context.applicationContext, PreferenceProviderEntryPoint::class.java)
            .preferenceProvider()
    val liveMatchesResponse = LiveScoreApiService.getLiveScoreApi().getLiveMatches()
    Log.d("LIVE_SCORE_RESPONSE", liveMatchesResponse.toString())
    preferenceProvider.prefsDatastore().edit { store ->
        store[LIVE_MATCHES_RESPONSE_KEY] = Gson().toJson(liveMatchesResponse)
    }
    context.sendWidgetUpdateBroadcast<LiveCricketScoreWidgetReceiver>()
}

suspend fun updateCOVID19Widget(context: Context) {
    val preferenceProvider =
        EntryPoints.get(context.applicationContext, PreferenceProviderEntryPoint::class.java)
            .preferenceProvider()
    val summaryResponse = COVIDApiService.getCOVIDApi().getSummary()
    Log.d("SUMMARY_RESPONSE", summaryResponse.toString())
    preferenceProvider.prefsDatastore().edit { store ->
        store[SUMMARY_RESPONSE_KEY] = Gson().toJson(summaryResponse)
    }
    context.sendWidgetUpdateBroadcast<COVID19WidgetReceiver>()
}

inline fun <reified T> Context.sendWidgetUpdateBroadcast() {
    val componentName = ComponentName(this, T::class.java)
    val appWidgetIds = AppWidgetManager.getInstance(this).getAppWidgetIds(componentName)
    Intent(this, T::class.java).apply {
        this.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        this.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
    }.also {
        this.sendBroadcast(it)
    }
}
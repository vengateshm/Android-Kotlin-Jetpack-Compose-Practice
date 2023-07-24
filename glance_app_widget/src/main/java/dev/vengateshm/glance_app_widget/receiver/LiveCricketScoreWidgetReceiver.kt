package dev.vengateshm.glance_app_widget.receiver

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import dev.vengateshm.glance_app_widget.widget.LiveCricketScoreWidget

class LiveCricketScoreWidgetReceiver : GlanceAppWidgetReceiver() {

    private val tag = "LiveCricketScoreWidRx"

    override val glanceAppWidget: GlanceAppWidget
        get() = LiveCricketScoreWidget()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d(tag, "Live cricket score widget updated")
    }
}
package dev.vengateshm.glance_app_widget.receiver

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import dev.vengateshm.glance_app_widget.widget.COVID19Widget

class COVID19WidgetReceiver : GlanceAppWidgetReceiver() {

    private val tag = COVID19WidgetReceiver::class.java.simpleName

    override val glanceAppWidget: GlanceAppWidget
        get() = COVID19Widget()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d(tag, "Widget updated $appWidgetIds")
    }
}

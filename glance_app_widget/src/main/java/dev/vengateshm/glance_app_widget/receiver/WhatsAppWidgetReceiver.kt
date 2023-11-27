package dev.vengateshm.glance_app_widget.receiver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import dev.vengateshm.glance_app_widget.widget.WhatsAppWidget

class WhatsAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WhatsAppWidget()
}
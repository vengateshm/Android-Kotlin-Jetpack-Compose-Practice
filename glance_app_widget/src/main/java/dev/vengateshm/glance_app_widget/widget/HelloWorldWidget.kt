package dev.vengateshm.glance_app_widget.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import dev.vengateshm.glance_app_widget.R

class HelloWorldWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
//            Text(text = "Hello, World!")

            GlanceTheme {
                Scaffold(
//                    backgroundColor = GlanceTheme.colors.surface,
                    backgroundColor = GlanceTheme.colors.widgetBackground,
                    titleBar = {
                        TitleBar(
                            startIcon = ImageProvider(R.drawable.baseline_android_24),
                            title = "Example"
                        )
                    }
                ) {
                    Text(
                        text = "Hello, World!",
                        style = TextStyle(color = GlanceTheme.colors.onSurface)
                    )
                }
            }
        }
    }

    override fun onCompositionError(
        context: Context,
        glanceId: GlanceId,
        appWidgetId: Int,
        throwable: Throwable
    ) {
        val rv = RemoteViews(context.packageName, R.layout.error_layout)
        rv.setTextViewText(
            R.id.error_text_view,
            "Error was thrown.\nThis is a custom view \nError Message: ${throwable.message}"
        )
        // rv.setOnClickPendingIntent(R.id.error_text_view, getErrorIntent(context, throwable))
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, rv)
    }
}

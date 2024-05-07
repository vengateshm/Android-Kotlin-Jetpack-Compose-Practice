package dev.vengateshm.glance_app_widget.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import dev.vengateshm.glance_app_widget.GlanceAppWidgetActivity

private val counterKey = ActionParameters.Key<Int>("counter_key")

class CounterGlanceAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val preferences = currentState<Preferences>()
            val count = preferences[intPreferencesKey(counterKey.name)] ?: 0
            CounterWidgetContent(count = count)
        }
    }

    @Composable
    private fun CounterWidgetContent(count: Int) {
        Column(
            modifier = GlanceModifier.fillMaxSize().background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = GlanceModifier.padding(16.dp),
                text = count.toString()
            )
            Row(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    text = "Count",
                    onClick = actionRunCallback<IncrementAction>(
                        actionParametersOf(counterKey to count)
                    )
                )
                Spacer(modifier = GlanceModifier.width(16.dp))
                Button(
                    text = "Open App",
                    onClick = actionStartActivity<GlanceAppWidgetActivity>()
                )
            }
        }
    }
}

class IncrementAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val count = parameters[counterKey] ?: 0
        updateAppWidgetState(context, glanceId) { mutablePreferences ->
            mutablePreferences[intPreferencesKey(counterKey.name)] = if (count >= 10) 0 else count + 1
        }
        CounterGlanceAppWidget().update(context, glanceId)
    }
}
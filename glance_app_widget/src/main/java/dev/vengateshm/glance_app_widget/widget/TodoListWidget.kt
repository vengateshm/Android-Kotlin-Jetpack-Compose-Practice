package dev.vengateshm.glance_app_widget.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.appwidget.CheckBox
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.ToggleableStateKey
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.currentState
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import dev.vengateshm.glance_app_widget.R

private val groceryStringIds = listOf(
    R.string.grocery_list_milk,
    R.string.grocery_list_eggs,
    R.string.grocery_list_tomatoes,
    R.string.grocery_list_bacon,
    R.string.grocery_list_butter,
    R.string.grocery_list_cheese,
    R.string.grocery_list_potatoes,
    R.string.grocery_list_broccoli,
    R.string.grocery_list_salmon,
    R.string.grocery_list_yogurt
)

class TodoListWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*>
        get() = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val prefs = currentState<Preferences>()
            GlanceTheme {
                Scaffold(
                    modifier = GlanceModifier.fillMaxSize(),
                    titleBar = {
                        TitleBar(
                            startIcon = ImageProvider(R.drawable.baseline_checklist_24),
                            title = "ToDos"
                        )
                    },
                    backgroundColor = GlanceTheme.colors.widgetBackground
                ) {
                    Column(
                        modifier = GlanceModifier
                            .fillMaxSize()
                            /*.background(ImageProvider(R.drawable.app_widget_background))
                            .appWidgetBackground()*/
                            .padding(16.dp)
                    ) {
                        /*Text(
                            text = context.getString(R.string.todo_list),
                            modifier = GlanceModifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            ),
                        )*/
                        CountChecked(
                            groceryStringIds.filter {
                                prefs[booleanPreferencesKey(it.toString())] ?: false
                            }.size
                        )
                        LazyColumn {
                            items(groceryStringIds) {
                                val idString = it.toString()
                                val checked = prefs[booleanPreferencesKey(idString)] ?: false
                                CheckBox(
                                    text = context.getString(it),
                                    checked = checked,
                                    onCheckedChange = actionRunCallback<CheckboxClickAction>(
                                        actionParametersOf(
                                            toggledStringIdKey to idString
                                        )
                                    ),
                                    modifier = GlanceModifier.padding(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CountChecked(checkedCount: Int) {
    Text(
        style = TextStyle(color = GlanceTheme.colors.onSurface),
        text = "$checkedCount checkboxes checked",
        modifier = GlanceModifier.padding(start = 8.dp)
    )
}

private val toggledStringIdKey = ActionParameters.Key<String>("ToggledStringIdKey")

class CheckboxClickAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val toggledStringId = requireNotNull(parameters[toggledStringIdKey]) {
            "Add $toggledStringIdKey parameter in the ActionParameters."
        }

        // The checked state of the clicked checkbox can be added implicitly to the parameters and
        // can be retrieved by using the ToggleableStateKey
        val checked = requireNotNull(parameters[ToggleableStateKey]) {
            "This action should only be called in response to toggleable events"
        }
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) {
            it.toMutablePreferences()
                .apply { this[booleanPreferencesKey(toggledStringId)] = checked }
        }
        TodoListWidget().update(context, glanceId)
    }
}

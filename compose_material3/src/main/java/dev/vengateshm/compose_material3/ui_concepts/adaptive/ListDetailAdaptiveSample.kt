package dev.vengateshm.compose_material3.ui_concepts.adaptive

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailAdaptiveSample(modifier: Modifier = Modifier) {
    /*var selectedItem: ProgrammingLanguage? by rememberSaveable(stateSaver = ProgrammingLanguage.Saver) {
        mutableStateOf(null)
    }*/

    val navigator = rememberListDetailPaneScaffoldNavigator<ProgrammingLanguage>()

    BackHandler(enabled = navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane(modifier = Modifier) {
                ProgrammingLanguageList(
                    onClick = {
                        //selectedItem = it
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
                    })
            }
        },
        detailPane = {
            AnimatedPane(modifier = Modifier) {
                /*selectedItem?.run {
                    Detail(programmingLanguage = this)
                }*/
                navigator.currentDestination?.content?.run {
                    Detail(programmingLanguage = this)
                }
            }
        },
        extraPane = {
            AnimatedPane(modifier = Modifier) {
                Text(text = "Extra Pane")
            }
        })
}

@Composable
fun ProgrammingLanguageList(onClick: (ProgrammingLanguage) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(ProgrammingLanguage.getProgrammingLanguages()) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = item.icon, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(width = 16.dp))
                Text(
                    modifier = Modifier
                        .clickable {
                            onClick(item)
                        },
                    text = item.name,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun Detail(programmingLanguage: ProgrammingLanguage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = programmingLanguage.icon, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(height = 16.dp))
        Text(text = programmingLanguage.name, fontSize = 24.sp)
    }
}

@Parcelize
data class ProgrammingLanguage(
    val id: Int,
    val name: String,
    val icon: String,
) : Parcelable {
    companion object {
        fun getProgrammingLanguages(): List<ProgrammingLanguage> {
            val languages = listOf(
                ProgrammingLanguage(1, "Python", "\uD83D\uDC0D"),
                ProgrammingLanguage(2, "Java", "\u2615"),
                ProgrammingLanguage(3, "JavaScript", "\uD83C\uDF10"),
                ProgrammingLanguage(5, "C", "\uD83C\uDF4E"),
                ProgrammingLanguage(7, "Swift", "\uD83D\uDC26"),
                ProgrammingLanguage(8, "Kotlin", "\uD83C\uDFAF"),
                ProgrammingLanguage(9, "Ruby", "\uD83D\uDC8E"),
                ProgrammingLanguage(10, "PHP", "\uD83D\uDC18"),
                ProgrammingLanguage(11, "Go", "\uD83D\uDC39"),
                ProgrammingLanguage(12, "Rust", "\uD83E\uDD80"),
                ProgrammingLanguage(13, "Haskell", "\u03BB"),
                ProgrammingLanguage(14, "Scala", "\uD83D\uDE80"),
                ProgrammingLanguage(15, "Perl", "\uD83D\uDC2A"),
                ProgrammingLanguage(16, "Lua", "\uD83C\uDF19"),
                ProgrammingLanguage(17, "HTML/CSS", "\uD83C\uDF10"),
                ProgrammingLanguage(18, "SQL", "\uD83D\uDCCA"),
                ProgrammingLanguage(19, "Assembly", "\u2699"),
                ProgrammingLanguage(20, "Shell Scripting", "\uD83D\uDC1A")
            )

            return languages
        }

        val Saver: Saver<ProgrammingLanguage?, Map<String, Any>> = Saver(
            save = { item ->
                item?.run {
                    mapOf(
                        "id" to item.id,
                        "name" to item.name,
                        "icon" to item.icon
                    )
                }
            },
            restore = { map ->
                ProgrammingLanguage(
                    id = map["id"] as Int,
                    name = map["name"] as String,
                    icon = map["icon"] as String
                )
            }
        )
    }
}

@Preview
@Composable
private fun ListDetailAdaptiveSamplePreview() {
    ListDetailAdaptiveSample()
}
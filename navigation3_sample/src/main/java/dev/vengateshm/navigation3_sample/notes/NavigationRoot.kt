package dev.vengateshm.navigation3_sample.notes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import kotlinx.serialization.Serializable

@Serializable
data object NoteListScreen : NavKey

@Serializable
data class NoteDetailsScreen(val id: Int) : NavKey

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(NoteListScreen)
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator(),
        ),
//        sceneStrategy = DialogSceneStrategy(),
        sceneStrategy = TwoPaneSceneStrategy(),
        entryProvider = { key ->
            when (key) {
                is NoteListScreen -> {
                    NavEntry(key = key) {
                        NoteListScreen(
                            onNoteClick = {
                                backStack.add(NoteDetailsScreen(it))
                            },
                        )
                    }
                }

                is NoteDetailsScreen -> {
                    NavEntry(
                        key = key,
//                        metadata = DialogSceneStrategy.dialog(),
                        metadata = TwoPaneScene.twoPane(),
                    ) {
                        NoteDetailsScreenUi(
                            viewModel = viewModel(factory = NoteDetailViewModel.Factory(key)),
                        )
                    }
                }

                else -> error("Unknown screen: $key")
            }
        },
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NavigationRootPreview() {
    NavigationRoot()
}
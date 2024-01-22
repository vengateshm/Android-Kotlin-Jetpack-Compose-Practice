package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.screen.character_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onCharacterClicked: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold { padding ->
        LazyColumn(contentPadding = padding) {
            items(state.characterList) { character ->
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCharacterClicked(character.id)
                            },
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model =
                                ImageRequest.Builder(context)
                                    .data(character.image)
                                    .crossfade(true)
                                    .build(),
                            modifier = Modifier.size(64.dp),
                            contentDescription = null,
                        )
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                        ) {
                            Text(text = character.name, fontSize = 16.sp)
                            Text(text = character.species, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

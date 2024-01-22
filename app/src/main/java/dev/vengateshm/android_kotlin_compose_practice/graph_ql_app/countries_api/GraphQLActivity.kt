package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.countries_api

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class GraphQLActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ContinentListScreen()
            }
        }
    }
}

@Composable
fun ContinentListScreen() {
    val repository = remember { ApiRepository() }
    var continents by remember { mutableStateOf(emptyList<Continent>()) }
    LaunchedEffect(key1 = true) {
        val continentsList = repository.getContinents()
        continents = continentsList
        Log.d("Continents : ", "$continentsList")
        // val countries = repository.getCountriesByContinent("OC")
        // Log.d("Countries : ", "$countries")
    }
    ContinentList(continents = continents)
}

@Composable
fun ContinentList(continents: List<Continent>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(continents) { continent ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = continent.name)
                Divider()
            }
        }
    }
}

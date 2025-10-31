package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

data class City(
  val name: String,
  val continent: String,
)

data class ScreenWithSearchBarState(
  val searchQuery: String = "",
  val cities: ImmutableList<City> = persistentListOf(),
) {
  val filteredCities: ImmutableList<City>
    get() = cities.filter {
      it.name.contains(searchQuery, ignoreCase = true) ||
          it.continent.contains(searchQuery, ignoreCase = true)
    }.toPersistentList()
}

@Composable
fun ScreenWithSearchBar(
  state: ScreenWithSearchBarState,
  onSearchQueryUpdate: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    topBar = {
      CustomSearchBar(
        state = state,
        onQueryUpdate = onSearchQueryUpdate,
        onCitySelected = { city ->
          println("Selected City: $city")
        },
      )
    },
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
    ) {
      CitiesList(
        modifier = Modifier.fillMaxSize(),
        cities = state.cities,
        onCitySelected = {

        },
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomSearchBar(
  state: ScreenWithSearchBarState,
  onQueryUpdate: (newValue: String) -> Unit,
  onCitySelected: (City) -> Unit,
) {
  var isSearchExpanded by remember { mutableStateOf(false) }
  val searchBarPadding by animateDpAsState(
    targetValue = if (isSearchExpanded) 0.dp else 16.dp,
    label = "searchPadding",
  )

  SearchBar(
    modifier = Modifier
      .fillMaxWidth()
      .padding(searchBarPadding),
    shape = RoundedCornerShape(16.dp),
    colors = SearchBarDefaults.colors(
      containerColor = MaterialTheme.colorScheme.primary.copy(0.5f),
      dividerColor = Color.Red,
    ),
    inputField = {
      SearchBarDefaults.InputField(
        query = state.searchQuery,
        onQueryChange = onQueryUpdate,
        onSearch = {

        },
        leadingIcon = {
          IconButton(
            onClick = {
              if (isSearchExpanded) {
                onQueryUpdate("")
              }
              isSearchExpanded = !isSearchExpanded
            },
          ) {
            Icon(
              imageVector = if (isSearchExpanded) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Search,
              tint = MaterialTheme.colorScheme.onBackground,
              contentDescription = if (isSearchExpanded) "Close Search" else "Search",
            )
          }
        },
        placeholder = {
          Text(
            text = "Search cities",
            style = MaterialTheme.typography.bodyMedium,
          )
        },
        colors = TextFieldDefaults.colors(
          cursorColor = MaterialTheme.colorScheme.onBackground,
          focusedTextColor = MaterialTheme.colorScheme.onBackground,
          unfocusedTextColor = MaterialTheme.colorScheme.onBackground.copy(.5f),
          focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(.5f),
          unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(.5f),
        ),
        expanded = isSearchExpanded,
        onExpandedChange = { isExpanded ->
          isSearchExpanded = isExpanded
        },
      )
    },
    expanded = isSearchExpanded,
    onExpandedChange = { isExpanded -> isSearchExpanded = isExpanded },
    content = {
      Box(modifier = Modifier.fillMaxSize()) {
        CitiesList(
          cities = state.filteredCities,
          onCitySelected = onCitySelected,
        )
        if (state.filteredCities.isEmpty()) {
          Text(
            text = "No matching cities found",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge,
          )
        }
      }
    },
  )
}

@Composable
private fun CitiesList(
  modifier: Modifier = Modifier,
  cities: ImmutableList<City>,
  onCitySelected: (City) -> Unit,
) {
  LazyColumn(
    modifier = modifier,
  ) {
    items(items = cities, key = { it.name + it.continent }) {
      ListItem(
        modifier = Modifier.clickable { onCitySelected(it) },
        colors = ListItemDefaults.colors(
          containerColor = MaterialTheme.colorScheme.background,
        ),
        headlineContent = {
          Text(
            text = it.name,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelLarge,
          )
        },
        supportingContent = {
          Text(
            text = it.continent,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
            style = MaterialTheme.typography.labelMedium,
          )
        },
      )
    }
  }
}

@Preview
@Composable
private fun ScreenWithSearchBarPreview() {
  var state by remember {
    mutableStateOf(
      ScreenWithSearchBarState(
        cities = sampleCities,
      ),
    )
  }
  MaterialTheme {
    ScreenWithSearchBar(
      state = state,
      onSearchQueryUpdate = { newQuery ->
        state = state.copy(searchQuery = newQuery)
      },
    )
  }
}

val sampleCities = listOf(
  City(name = "New York", continent = "North America"),
  City(name = "Los Angeles", continent = "North America"),
  City(name = "Toronto", continent = "North America"),
  City(name = "London", continent = "Europe"),
  City(name = "Paris", continent = "Europe"),
  City(name = "Berlin", continent = "Europe"),
  City(name = "Tokyo", continent = "Asia"),
  City(name = "Seoul", continent = "Asia"),
  City(name = "Beijing", continent = "Asia"),
  City(name = "Sydney", continent = "Australia"),
  City(name = "Melbourne", continent = "Australia"),
  City(name = "Cairo", continent = "Africa"),
  City(name = "Nairobi", continent = "Africa"),
  City(name = "Cape Town", continent = "Africa"),
  City(name = "São Paulo", continent = "South America"),
  City(name = "Buenos Aires", continent = "South America"),
  City(name = "Lima", continent = "South America"),
).toPersistentList()

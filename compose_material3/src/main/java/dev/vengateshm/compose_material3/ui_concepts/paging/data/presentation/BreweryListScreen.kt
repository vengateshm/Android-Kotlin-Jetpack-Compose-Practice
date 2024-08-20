package dev.vengateshm.compose_material3.ui_concepts.paging.data.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel

@Composable
fun BreweryListScreen() {
    val viewModel = koinViewModel<BreweriesViewModel>()
    val breweries = viewModel.breweryFlow.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(key1 = breweries.loadState) {
        if (breweries.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (breweries.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (breweries.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = breweries.itemCount,
                    key = { breweries[it]!!.id },
                ) { index ->
                    val item = breweries[index]
                    item?.let {
                        BreweryItem(
                            brewery = it,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    if (breweries.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
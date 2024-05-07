package dev.vengateshm.compose_material3.ui_concepts.infinite_scroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

class InfiniteScrollActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val photoRepo = PhotoRepoImpl(ApiClient.getService(PhotoService::class.java))

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<PhotoListViewModel>(
                        factory = PhotoListViewModelFactory(photoRepo)
                    )
                    PhotoListScreen(viewModel = viewModel)
                }
            }
        }
    }
}
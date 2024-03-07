package dev.vengateshm.android_kotlin_compose_practice.res_strings_cache_in_viewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import dev.vengateshm.android_kotlin_compose_practice.R

class ResStringCacheInViewModelActivity : ComponentActivity() {

    private lateinit var viewModel: ResStringsCacheInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ResStringCache", "onCreate() called")

        val viewmodelFactory = ResStringsCacheInViewModelProvider(
            ResStrings(
                firstNameLabel = getString(R.string.first_name_label),
                lastNameLabel = getString(R.string.last_name_label),
            )
        )
        viewModel =
            ViewModelProvider(this, viewmodelFactory)[ResStringsCacheInViewModel::class.java]

        setContent {
            MaterialTheme {
                Surface {
                    Column(modifier = Modifier.fillMaxSize()) {
//                        Text(text = viewModel.getFirstNameLabel())
                        Text(text = stringResource(id = R.string.first_name_label))
                        Text(text = "Vengatesh")
//                        Text(text = viewModel.getLastNameLabel())
                        Text(text = stringResource(id = R.string.last_name_label))
                        Text(text = "M")
                    }
                }
            }
        }
    }
}
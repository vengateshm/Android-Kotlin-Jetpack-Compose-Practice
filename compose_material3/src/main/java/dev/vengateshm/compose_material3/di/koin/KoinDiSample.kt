package dev.vengateshm.compose_material3.di.koin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.vengateshm.compose_material3.di.koin.ui.AnnotatedViewModel
import dev.vengateshm.compose_material3.di.koin.ui.KoinDiViewModel
import dev.vengateshm.compose_material3.ui_components.CenterAlignedContent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun KoinDiSample(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<KoinDiViewModel>()
    val annotatedViewModel =
        koinViewModel<AnnotatedViewModel> { parametersOf(Human(name = "Vengatesh")) }
    val uiData by viewModel.uiData.collectAsStateWithLifecycle()
    val uiData1 by annotatedViewModel.uiData.collectAsStateWithLifecycle()

    CenterAlignedContent {
        if (uiData.isLoading || uiData1.isLoading) {
            Text(text = "Loading...")
        }
        if (uiData.data != null || uiData1.data != null) {
            Text(text = uiData.data.orEmpty().plus(uiData1.data.orEmpty()))
        }
    }
}
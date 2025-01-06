package dev.vengateshm.compose_material3.api_compose.fragments_as_composables

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.fragment.compose.AndroidFragment
import androidx.fragment.compose.rememberFragmentState

@Composable
fun ProfileFragmentComposable(modifier: Modifier = Modifier) {
    val onTextClicked = remember {
        {
            println("Text clicked")
        }
    }
    AndroidFragment<ProfileFragment>(
        modifier = Modifier.safeContentPadding(),
        fragmentState = rememberFragmentState(),
        arguments = bundleOf(
            "id" to 1,
            "name" to "Vengatesh",
        ),
        onUpdate = { fragment ->
            println("Called on update")
            fragment.onTextClicked = onTextClicked
        },
    )
}

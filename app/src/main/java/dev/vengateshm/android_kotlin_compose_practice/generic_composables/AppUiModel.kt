package dev.vengateshm.android_kotlin_compose_practice.generic_composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Interface to get unique id from each model
// which implements this
interface AppUiModel {
    fun id(): Int
}

// Your model
data class Cheese(val id: Int, val name: String) : AppUiModel {
    override fun id() = id
}

// Usage
@Composable
fun CheeseFactory() {
    val list =
        remember {
            listOf(
                Cheese(id = 1, "Parmesan"),
                Cheese(id = 2, "Cheddar"),
            )
        }
    AppLazyColumn(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        items = list,
    ) { cheese ->
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
        ) {
            Text(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                text = cheese.name,
            )
        }
    }
}

@Preview
@Composable
fun CheeseFactoryPreview() {
    CheeseFactory()
}

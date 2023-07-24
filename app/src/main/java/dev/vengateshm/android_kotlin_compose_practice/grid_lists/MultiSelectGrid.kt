package dev.vengateshm.android_kotlin_compose_practice.grid_lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalFoundationApi
@Composable
fun MultiSelectVerticalGridListScreen() {
    val selectedItems = rememberSaveable {
        mutableStateOf(setOf<Int>())
    }

    MultiSelectVerticalGridList(selectedItems = selectedItems.value,
        onCheckBoxCheckStateChanged = { isChecked, index ->
            selectedItems.value = if (isChecked)
                selectedItems.value + index
            else
                selectedItems.value - index
        })
}

@ExperimentalFoundationApi
@Composable
fun MultiSelectVerticalGridList(
    selectedItems: Set<Int>,
    onCheckBoxCheckStateChanged: (Boolean, Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(30) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, end = 8.dp)
                    ) {
                        Checkbox(modifier = Modifier.align(Alignment.End),
                            checked = selectedItems.contains(index),
                            onCheckedChange = { isChecked ->
                                onCheckBoxCheckStateChanged(
                                    isChecked,
                                    index
                                )
                            })
                    }
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Item ${index + 1}",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}
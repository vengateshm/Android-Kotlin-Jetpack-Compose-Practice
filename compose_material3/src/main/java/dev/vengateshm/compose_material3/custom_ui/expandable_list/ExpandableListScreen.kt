package dev.vengateshm.compose_material3.custom_ui.expandable_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableListScreen(modifier: Modifier = Modifier) {
    val isExpanded =
        remember { mutableStateMapOf<String, Boolean>() }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(phoneList, key = { it.hashCode() }) { phone ->
            ExpandableContent(expandableItem = phone,
                isExpanded = isExpanded[phone.brand] ?: false,
                onArrowClicked = { brand ->
                    if (isExpanded[brand] == null) {
                        isExpanded[brand] = true
                    } else {
                        isExpanded[brand] = !isExpanded.getValue(brand)
                    }
                    //Clear others
                    isExpanded.forEach {
                        if (isExpanded[it.key] != null && it.key != brand) {
                            isExpanded[it.key] = false
                        }
                    }
                })
        }
    }
}

@Composable
fun ExpandableContent(
    expandableItem: ExpandableItem<String, List<String>>,
    isExpanded: Boolean,
    onArrowClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onArrowClicked(expandableItem.parent())
            }
            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = expandableItem.parent())
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Drop down icon"
            )
        }
        AnimatedVisibility(
            visible = isExpanded
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                expandableItem.child().forEach { child ->
                    Text(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 16.dp)
                            .padding(vertical = 8.dp), text = child
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ExpandableListScreenPreview() {
    ExpandableListScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ExpandableContentPreview() {
    ExpandableContent(
        expandableItem = phoneList[0],
        isExpanded = true,
        onArrowClicked = {}
    )
}
package dev.vengateshm.android_kotlin_compose_practice.multi_header_lazy_cloumn

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MultiHeaderLazyColumn() {
    val dropDownState =
        remember {
            mutableStateMapOf<String, Boolean>()
        }

    LaunchedEffect(true) {
        dropDownState.apply {
            data.associate { it.type to false }
                .also {
                    putAll(it)
                }
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        grouped.forEach { (type, items) ->
            // Header
            item(key = type) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = dropDownState[type] == true,
                                onValueChange = {
                                    dropDownState[type] = it
                                },
                                role = Role.Button,
                            ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "$type (${items.size})",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            dropDownState[type] = !dropDownState[type]!!
                        },
                    ) {
                        Icon(
                            imageVector = if (dropDownState[type] == true) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                        )
                    }
                }
            }
            if (dropDownState[type] == true) {
                // Items
                items(items, key = { it.name }) { item ->
                    Text(text = item.name, fontSize = 14.sp)
                }
            }
        }
    }
}

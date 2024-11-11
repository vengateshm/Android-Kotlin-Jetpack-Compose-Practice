package dev.vengateshm.compose_material3.ui_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.compose_material3.theme.Material3AppTheme

@Composable
fun ListItemMaterial3Sample(modifier: Modifier = Modifier) {
    ListItem(
        headlineContent = {
            Text(text = "Title")
        },
        supportingContent = {
            Text(text = "Subtitle")
        },
        modifier = modifier,
        overlineContent = {
            Text(text = "Category")
        },
        leadingContent = {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
        },
        trailingContent = {
            Checkbox(checked = false, onCheckedChange = {})
        },
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ListItemMaterial3SamplePreview() {
    Material3AppTheme {
        ListItemMaterial3Sample()
    }
}
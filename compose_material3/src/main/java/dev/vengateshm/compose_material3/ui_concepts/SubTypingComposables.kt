package dev.vengateshm.compose_material3.ui_concepts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SubTypingComposables(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        MyToolbar(
            title = "Home",
            leadingButton = ToolbarButton(
                text = "Back",
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onClick = {},
            ),
            trailingButton = ToolbarButton(
                text = "Search",
                imageVector = Icons.Default.Search,
                onClick = {},
            ),
            trailingButtonSecondary = CartButton(),
        )
    }
}

@Composable
fun MyToolbar(
    modifier: Modifier = Modifier,
    title: String,
    leadingButton: ToolbarButton,
    trailingButton: ToolbarButton,
    trailingButtonSecondary: MyButton,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingButton()
        Text(text = title, modifier = Modifier.weight(weight = 1f))
        trailingButton()
        trailingButtonSecondary()
    }
}

class ToolbarButton(
    private val text: String,
    private val imageVector: ImageVector,
    private val onClick: () -> Unit,
) : @Composable () -> Unit {
    @Composable
    override fun invoke() {
        IconButton(onClick = onClick) {
            Icon(imageVector = imageVector, contentDescription = "$text toolbar icon")
        }
    }
}

abstract class MyButton : @Composable () -> Unit {
    @Composable
    abstract override fun invoke()
}

class CartButton(private val onClick: () -> Unit = {}) : MyButton() {
    @Composable
    override fun invoke() {
        IconButton(onClick = onClick) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "shopping cart icon")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun SubTypingComposablesPreview() {
    SubTypingComposables()
}
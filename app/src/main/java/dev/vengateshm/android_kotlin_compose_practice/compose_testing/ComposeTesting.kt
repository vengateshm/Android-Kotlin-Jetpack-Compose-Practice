package dev.vengateshm.android_kotlin_compose_practice.compose_testing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.R

@Composable
fun FavouriteButton(modifier: Modifier = Modifier) {
    var isFavourite by remember {
        mutableStateOf(false)
    }
    Row(
        modifier =
            modifier
                .clickable {
                    isFavourite = !isFavourite
                }
                .testTag("FAV_BTN")
                .background(
                    color = Color.Blue,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite_24),
            contentDescription = "Favourite icon",
            tint = if (isFavourite) Color.Red else Color.LightGray,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = if (isFavourite) "Liked" else "Like",
            color = Color.White.copy(alpha = 0.6f),
        )
    }
}

@Preview
@Composable
fun FavouriteButtonPreview() {
    FavouriteButton()
}

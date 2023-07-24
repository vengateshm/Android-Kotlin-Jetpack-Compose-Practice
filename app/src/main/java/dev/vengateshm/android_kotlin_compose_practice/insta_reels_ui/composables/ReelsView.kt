package dev.vengateshm.android_kotlin_compose_practice.insta_reels_ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import dev.vengateshm.android_kotlin_compose_practice.R
import dev.vengateshm.android_kotlin_compose_practice.insta_reels_ui.mockData.MockData
import dev.vengateshm.android_kotlin_compose_practice.insta_reels_ui.models.Reel

val verticalPadding = 16.dp
val horizontalPadding = 12.dp

@Composable
fun ReelsView() {
    Box(
        modifier = Modifier
            .background(color = Color.Black)
    ) {
        ReelsList()
        ReelsHeader()
    }
}

@Composable
fun ReelsList() {
    val reelList = MockData.getReelList()
    LazyColumn {
        items(reelList.size) { index ->
            Box(
                modifier = Modifier
                    .fillParentMaxSize()
            ) {

                // Bottom controls
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                ) {
                    ReelFooter(reelList[index])
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ReelFooter(reel: Reel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 18.dp,
                bottom = 18.dp
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        FooterUserData(
            reel = reel,
            modifier = Modifier.weight(8f)
        )

        FooterUserAction(
            reel = reel,
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
fun FooterUserData(reel: Reel, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                imageModel = reel.userImage,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(28.dp)
                    .background(color = Color.Gray, shape = CircleShape)
                    .clip(CircleShape),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(horizontalPadding))

            Text(
                text = reel.userName,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.width(horizontalPadding))
//            Canvas(modifier = Modifier.size(5.dp), onDraw = {
//                drawCircle(
//                    color = Color.White,
//                    radius = 8f
//                )
//            })
            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text = "Follow",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .border(
                        1.dp, shape = RoundedCornerShape(2.dp),
                        color = Color.White
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
            )
        }

        Spacer(modifier = Modifier.height(horizontalPadding))
        Text(text = reel.comment, color = Color.White)
        Spacer(modifier = Modifier.height(horizontalPadding))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(reel.userName, color = Color.White)
            Spacer(modifier = Modifier.width(horizontalPadding))
            Canvas(modifier = Modifier.size(5.dp), onDraw = {
                drawCircle(
                    color = Color.White,
                    radius = 2f
                )
            })
            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text = "Original Audio",
                color = Color.White
            )
        }
    }
}

@Composable
fun FooterUserAction(reel: Reel, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        UserActionWithText(
            drawableRes = R.drawable.ic_outlined_favorite,
            label = reel.likesCount.toString()
        )
        Spacer(modifier = Modifier.height(24.dp))
        UserActionWithText(
            drawableRes = R.drawable.ic_outlined_comment,
            label = reel.commentsCount.toString()
        )
        Spacer(modifier = Modifier.height(24.dp))
        UserAction(drawableRes = R.drawable.ic_dm)
        Spacer(modifier = Modifier.height(24.dp))
        Icon(imageVector = Icons.Default.MoreVert, tint = Color.White, contentDescription = null)
        Spacer(modifier = Modifier.height(24.dp))
        GlideImage(
            imageModel = reel.userImage,
            modifier = Modifier
                .size(28.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(6.dp))
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = null
        )
    }
}

@Composable
fun UserAction(@DrawableRes drawableRes: Int) {
    Icon(
        bitmap = ImageBitmap.imageResource(id = drawableRes),
        tint = Color.White,
        modifier = Modifier.size(16.dp),
        contentDescription = null
    )
}

@Composable
fun UserActionWithText(@DrawableRes drawableRes: Int, label: String) {
    Icon(
        bitmap = ImageBitmap.imageResource(id = drawableRes),
        tint = Color.White,
        modifier = Modifier.size(28.dp),
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = label,
        color = Color.White,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun ReelsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Reels",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp
        )
        Icon(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_outlined_camera),
            tint = Color.White,
            modifier = Modifier
                .size(24.dp),
            contentDescription = "Camera Icon"
        )
    }
}

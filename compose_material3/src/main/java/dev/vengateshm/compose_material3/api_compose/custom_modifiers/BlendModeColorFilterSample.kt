package dev.vengateshm.compose_material3.api_compose.custom_modifiers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BlendModeColorFilterSample(modifier: Modifier = Modifier) {
    val imageList = remember {
        listOf(
            R.drawable.cmaterial3_scene_1,
            R.drawable.cmaterial3_scene_2,
            R.drawable.cmaterial3_scene_3,
        )
    }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { imageList.size })
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center,
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.colorFilter(
                    ColorFilter.colorMatrix(
                        ColorMatrix().apply {
                            setToSaturation(0f)
                        },
                    ),
                ),
            ) { page ->
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Text(
                fontSize = 48.sp,
                color = Color.White,
                text = "Scenic Views",
                modifier = Modifier.blendMode(BlendMode.Difference),
            )
        }
    }
}
package dev.vengateshm.android_kotlin_compose_practice.onboarding_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import dev.vengateshm.android_kotlin_compose_practice.R
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.Grey300
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.Grey900
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.WalkthroughScreenTypography

@ExperimentalPagerApi
class WalkthroughScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey_900)

        /*setContent {
            OnBoardingScreenTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val items = listOf<OnBoardingData>(
                        OnBoardingData(
                            R.drawable.ic_illustration_shopping,
                            "Shop Awesome Products",
                            "Shop from wide range of products from apparels, electronics, cosmetics and much more"
                        ),
                        OnBoardingData(
                            R.drawable.ic_illustration_delivery,
                            "One Day Delivery",
                            ""
                        ),
                        OnBoardingData(
                            R.drawable.ic_illustration_research,
                            "Amazing Customer Support",
                            ""
                        ),
                    )

//                    val pagerState = rememberPagerState(
//                        pageCount = items.size,
//                        initialOffscreenLimit = 2,
//                        infiniteLoop = false,
//                        initialPage = 0
//                    )

                    OnBoardingPager(
                        items = items,
                        pagerState = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Grey900)
                    )
                }
            }
        }*/
    }
}

@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    items: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    /*Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page: Int ->
                Column(
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = items[page].imageRes),
                        contentDescription = items[page].title
                    )

                    Text(
                        text = items[page].title,
                        modifier = Modifier.padding(top = 50.dp),
                        color = Color.White,
                        style = WalkthroughScreenTypography.body1
                    )
                    Text(
                        text = items[page].description,
                        modifier = Modifier.padding(
                            top = 30.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = WalkthroughScreenTypography.body1,
                        fontSize = 18.sp
                    )
                }
            }
            PagerIndicator(size = items.size, currentPage = pagerState.currentPage)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomSection(currentPage = pagerState.currentPage)
        }
    }*/
}

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 60.dp)
    ) {
        repeat(size) {
            Indicator(it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)
    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(color = if (isSelected) MaterialTheme.colors.primary else Grey300.copy(alpha = 0.3f))
    )
}

@Composable
fun BottomSection(currentPage: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = if (currentPage != 2) Arrangement.SpaceBetween else Arrangement.Center
    ) {
        if (currentPage == 2) {
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    modifier = Modifier.padding(
                        vertical = 8.dp,
                        horizontal = 40.dp
                    ),
                    text = "Get Started",
                    color = Grey900
                )
            }
        } else {
            SkipNextButton("Skip", Modifier.padding(start = 20.dp))
            SkipNextButton("Next", Modifier.padding(end = 20.dp))
        }
    }
}

@Composable
fun SkipNextButton(text: String, modifier: Modifier) {
    Text(
        text = text,
        color = Grey300,
        modifier = modifier,
        fontSize = 18.sp,
        style = WalkthroughScreenTypography.body1,
        fontWeight = FontWeight.Medium
    )
}
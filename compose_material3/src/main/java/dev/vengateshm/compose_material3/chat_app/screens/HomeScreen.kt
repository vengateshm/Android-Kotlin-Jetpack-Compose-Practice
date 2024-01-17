package dev.vengateshm.compose_material3.chat_app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.compose_material3.chat_app.data.Person
import dev.vengateshm.compose_material3.chat_app.data.personList
import dev.vengateshm.compose_material3.chat_app.navigation.CHAT
import dev.vengateshm.compose_material3.theme.DarkGray
import dev.vengateshm.compose_material3.theme.Gray
import dev.vengateshm.compose_material3.theme.InterBold
import dev.vengateshm.compose_material3.theme.InterMedium
import dev.vengateshm.compose_material3.theme.InterRegular
import dev.vengateshm.compose_material3.theme.InterSemiBold
import dev.vengateshm.compose_material3.theme.Line
import dev.vengateshm.compose_material3.theme.Yellow
import dev.vengateshm.compose_material3.R.string as AppStrings

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header()
            LazyRow(modifier = Modifier.padding(vertical = 20.dp)) {
                item {
                    AddStoryLayout(modifier = Modifier.padding(start = 23.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                }
                items(personList, key = { it.id }) {
                    UserStory(person = it)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .background(Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(
                        bottom = 15.dp,
                        top = 30.dp
                    )
                ) {
                    items(personList, key = { it.id }) {
                        UserEachRow(person = it) {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "data",
                                it
                            )
                            navHostController.navigate(CHAT)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontFamily = InterRegular,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300
            )
        ) {
            append("Welcome back, ")
        }
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontFamily = InterSemiBold,
                fontSize = 20.sp,
            )
        ) {
            append("Vengatesh!")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 60.dp)
    ) {
        Text(text = annotatedString)
    }
}


@Composable
fun AddStoryLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(
                    border = BorderStroke(1.5.dp, DarkGray),
                    shape = CircleShape
                )
                .background(color = Color.Black),
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Yellow)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier.size(12.dp),
                        tint = Yellow
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(AppStrings.add_story),
            style = TextStyle(
                color = Color.White,
                fontSize = 13.sp,
                fontFamily = InterMedium
            ), modifier = Modifier.align(CenterHorizontally)
        )
    }
}

@Composable
fun UserStory(
    person: Person,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(end = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(
                    border = BorderStroke(1.5.dp, Yellow),
                    shape = CircleShape
                )
                .background(Color.Black),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = person.icon),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp),
                    tint = Color.Unspecified
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = person.name,
            style = TextStyle(
                color = Color.White,
                fontSize = 13.sp,
                fontFamily = InterMedium
            ),
            modifier = Modifier.align(CenterHorizontally)
        )
    }
}

@Composable
fun UserEachRow(
    person: Person,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .noRippleClickable {
                onClick()
            }
            .padding(horizontal = 20.dp, vertical = 5.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = person.icon),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )
                    Column(
                    ) {
                        Text(
                            text = person.name,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = InterBold
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Okay", style = TextStyle(
                                color = Gray,
                                fontSize = 14.sp,
                                fontFamily = InterMedium
                            )
                        )
                    }

                }
                Text(
                    text = "12:23 PM",
                    style = TextStyle(
                        color = Gray,
                        fontSize = 12.sp,
                        fontFamily = InterMedium
                    )
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp, color = Line
            )
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
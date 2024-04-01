package dev.vengateshm.compose_material3.chat_app.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.compose_material3.R
import dev.vengateshm.compose_material3.chat_app.data.Chat
import dev.vengateshm.compose_material3.chat_app.data.Person
import dev.vengateshm.compose_material3.chat_app.data.chatList
import dev.vengateshm.compose_material3.theme.Gray
import dev.vengateshm.compose_material3.theme.Gray400
import dev.vengateshm.compose_material3.theme.InterBold
import dev.vengateshm.compose_material3.theme.InterRegular
import dev.vengateshm.compose_material3.theme.LightRed
import dev.vengateshm.compose_material3.theme.LightYellow
import dev.vengateshm.compose_material3.theme.Yellow

@Composable
fun ChatScreen(navHostController: NavHostController) {
    var message by remember { mutableStateOf("") }
    val data =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Person>("data") ?: Person()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            UserNameRow(
                person = data,
                modifier = Modifier.padding(
                    top = 60.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 25.dp)
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
                        start = 15.dp,
                        top = 25.dp,
                        end = 15.dp,
                        bottom = 50.dp
                    )
                ) {
                    items(chatList, key = { it.id }) {
                        ChatRow(chat = it)
                    }
                }
            }
        }
        CustomTextField(
            text = message,
            onValueChange = { message = it },
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ChatRow(
    chat: Chat
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (chat.direction) Alignment.Start else Alignment.End
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(
                    color = if (chat.direction) LightRed else LightYellow
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.message,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = InterRegular,
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 15.dp
                ),
                textAlign = TextAlign.End
            )
        }
        Text(
            text = chat.time,
            style = TextStyle(
                color = Gray,
                fontFamily = InterRegular,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
        )
    }
}

@Composable
fun CustomTextField(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(164.dp),
        border = BorderStroke(1.dp, Gray400)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            value = text,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.cmaterial3_type_message),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = InterRegular,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = { CommonIconButton(imageVector = Icons.Default.Add) },
            trailingIcon = { CommonIconButtonDrawable(R.drawable.cmaterial3_chat_app_mic) }
        )
    }
}

@Composable
fun CommonIconButton(
    imageVector: ImageVector
) {
    Box(
        modifier = Modifier
            .size(33.dp)
            .clip(CircleShape)
            .background(Yellow),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier.size(15.dp)
        )
    }
}

@Composable
fun CommonIconButtonDrawable(
    @DrawableRes icon: Int
) {
    Box(
        modifier = Modifier
            .size(33.dp)
            .clip(CircleShape)
            .background(Yellow), contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier.size(15.dp)
        )
    }
}

@Composable
fun UserNameRow(
    modifier: Modifier = Modifier,
    person: Person
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = person.icon),
                contentDescription = "",
                modifier = Modifier.size(42.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = person.name,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = InterBold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = stringResource(R.string.cmaterial3_online),
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = InterRegular,
                        fontSize = 14.sp
                    )
                )
            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(rememberNavController())
}
package dev.vengateshm.compose_material3.chat_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.compose_material3.chat_app.navigation.HOME
import dev.vengateshm.compose_material3.theme.Aqua
import dev.vengateshm.compose_material3.theme.InterBold
import dev.vengateshm.compose_material3.theme.InterSemiBold
import dev.vengateshm.compose_material3.R.drawable as Material3AppDrawable
import dev.vengateshm.compose_material3.R.string as Material3AppString

@Composable
fun StartScreen(
    navHostController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = Material3AppDrawable.cmaterial3_chat_app_background),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )

        Box(
            modifier = Modifier
                .padding(top = 220.dp)
                .align(Alignment.Center)
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 40.dp)
            ) {
                Text(
                    text = stringResource(Material3AppString.cmaterial3_stay_with_your_friends),
                    style = TextStyle(
                        fontSize = 36.sp,
                        color = Color.White,
                        fontFamily = InterBold
                    )
                )
                CustomCheckBox()

            }
        }
        Button(
            onClick = {
                navHostController.navigate(HOME)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter)
                .height(60.dp),
            shape = RoundedCornerShape(100.dp),

            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(Material3AppString.cmaterial3_get_started), style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = InterBold
                )
            )
        }
    }
}

@Composable
fun CustomCheckBox() {
    Row(
        modifier = Modifier.padding(vertical = 15.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp, topEnd = 10.dp, bottomStart = 80.dp, bottomEnd = 80.dp
                    )
                )
                .background(Aqua), contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Check,
                contentDescription = "",
                modifier = Modifier.size(15.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = stringResource(Material3AppString.cmaterial3_secure_private_messaging), style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = InterSemiBold
            )
        )
    }

}

@Preview
@Composable
fun CustomCheckBoxP() {
    CustomCheckBox()
}

@Preview
@Composable
fun StartScreenP() {
    StartScreen(rememberNavController())
}
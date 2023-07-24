package dev.vengateshm.android_kotlin_compose_practice.downloadable_fonts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.vengateshm.android_kotlin_compose_practice.R

class DownloadableFontActivity : ComponentActivity() {
    @OptIn(ExperimentalTextApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val provider = GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        )

        val arvo = GoogleFont("Arvo")
        val lobsterTwo = GoogleFont("Lobster Two")
        val ibmPlexSans = GoogleFont("IBM Plex Sans")

        val arvoFF = FontFamily(
            Font(
                googleFont = arvo, fontProvider = provider,
                weight = FontWeight.Bold, style = FontStyle.Italic
            )
        )
        val lobsterTwoFF = FontFamily(
            Font(
                googleFont = lobsterTwo, fontProvider = provider,
                weight = FontWeight.Normal, style = FontStyle.Normal
            )
        )
        val ibmPlexSansFF = FontFamily(
            Font(
                googleFont = ibmPlexSans, fontProvider = provider,
                weight = FontWeight.Thin, style = FontStyle.Normal
            )
        )

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = arvoFF,
                    text = "I am Arvo",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = lobsterTwoFF,
                    text = "I am Lobster Two",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = ibmPlexSansFF,
                    text = "I am IBM Plex Sans",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/*//        val lobsterTwo = GoogleFont("Lobster Two")
        val lobsterTwo = GoogleFont("IBM Plex Sans")
        val montserrat = GoogleFont("Montserrat")

        val lobsterTwoFF = FontFamily(
            Font(googleFont = lobsterTwo, fontProvider = provider, )
        )
        val montserratFF = FontFamily(
            Font(googleFont = lobsterTwo, fontProvider = provider)
        )*/
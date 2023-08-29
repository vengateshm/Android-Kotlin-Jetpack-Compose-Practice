package dev.vengateshm.android_kotlin_compose_practice.ad_integration

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView

class SampleAdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this) {
            Log.d("AD_INITIALIZE_STATUS", it.toString())
        }

        setContent {
            MaterialTheme {
                BannerAd()
            }
        }
    }
}

@Composable
fun BannerAd() {
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                val adView = AdManagerAdView(context)
                    .apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = "/6499/example/banner"
                    }
                val adRequest = AdManagerAdRequest.Builder().build()
                adView.loadAd(adRequest)
                adView
            }
        )
    }
}
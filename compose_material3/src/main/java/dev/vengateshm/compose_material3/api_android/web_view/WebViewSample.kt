package dev.vengateshm.compose_material3.api_android.web_view

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewSample(modifier: Modifier = Modifier) {
    // Use evaluateJavaScript for javascript
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                this.webViewClient = WebViewClient()
                this.settings.allowFileAccess = false
                this.settings.javaScriptEnabled = true
            }
        },
        update = { webview ->
            println("Update called")
            webview.loadUrl("http://www.traveltriangle.com")
        },
    )
}
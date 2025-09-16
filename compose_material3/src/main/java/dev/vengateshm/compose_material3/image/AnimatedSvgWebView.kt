package dev.vengateshm.compose_material3.image

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun AnimatedSvgWebView(modifier: Modifier = Modifier) {
  AndroidView(
    modifier = modifier,
    factory = { context ->
      WebView(context).apply {
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        webViewClient = WebViewClient()
      }
    },
    update = { webView ->
      val html = """
<html>
<body style="margin:0;padding:0;overflow:hidden">
<embed src="file:///android_asset/bouncingsquares.svg" type="image/svg+xml" />
</body>
</html>
        """
      webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
    },
  )
}

@Preview
@Composable
private fun AnimatedSvgWebViewPreview() {
  AnimatedSvgWebView()
}
package dev.vengateshm.android_kotlin_compose_practice.image_loader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object ImageLoader {
    private val handler = Handler(Looper.getMainLooper())

    fun loadImage(
        imageUrl: String,
        onSuccess: (Bitmap?) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        thread(start = true) {
            var bitmap: Bitmap?
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                bitmap = BitmapFactory.decodeStream(connection.inputStream)
                handler.post { onSuccess(bitmap) }
            } catch (e: Exception) {
                bitmap = null
                handler.post { onFailure(e.toString()) }
            }
        }
    }
}

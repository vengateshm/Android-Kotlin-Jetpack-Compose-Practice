package dev.vengateshm.compose_material3.compress_images

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream

fun Uri.toDrawable(context: Context): Drawable? {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(this)

    return inputStream.use {
        Drawable.createFromStream(inputStream, this.toString())
    }
}

fun Context.reduceImageSize(drawable: Drawable?): Bitmap? {
    drawable ?: return null

    val os = ByteArrayOutputStream()
    val bitmap = drawable.toBitmap(width = 200, height = 200, Bitmap.Config.ARGB_8888)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
    val byteArray = os.toByteArray()
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}
package dev.vengateshm.glance_app_widget.widget

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import dev.vengateshm.glance_app_widget.R

class WhatsAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            var isEdit by remember { mutableStateOf(false) }
            var mobileNumber by remember { mutableStateOf("") }
            if (isEdit) {
                Column(
                    modifier = GlanceModifier
                        .background(imageProvider = ImageProvider(R.drawable.summary_widget_bg))
                        .appWidgetBackground()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = mobileNumber
                    )
                    Row {
                        repeat(3) {
                            NumberButton(number = "${it + 1}") {
                                mobileNumber += "${it + 1}"
                            }
                        }
                    }
                    Row {
                        repeat(3) {
                            NumberButton(number = "${it + 4}") {
                                mobileNumber += "${it + 4}"
                            }
                        }
                    }
                    Row {
                        repeat(3) {
                            NumberButton(number = "${it + 7}") {
                                mobileNumber += "${it + 7}"
                            }
                        }
                    }
                    Row {
                        Image(
                            provider = ImageProvider(R.drawable.baseline_clear_all_24),
                            contentDescription = "Clear",
                            modifier = GlanceModifier
                                .size(40.dp)
                                .clickable {
                                    mobileNumber = ""
                                }
                                .padding(8.dp)
                        )
                        NumberButton(number = "0") {
                            mobileNumber += "0"
                        }
                        Image(
                            provider = ImageProvider(R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back",
                            modifier = GlanceModifier
                                .size(40.dp)
                                .clickable {
                                    val text = mobileNumber
                                    if (text.isNotEmpty()) {
                                        mobileNumber = text.substring(0, text.length - 1)
                                    }
                                }
                                .padding(8.dp)
                        )
                    }
                    Row {
                        Image(
                            provider = ImageProvider(R.drawable.baseline_power_settings_new_24),
                            contentDescription = "Close",
                            modifier = GlanceModifier
                                .size(40.dp)
                                .clickable {
                                    isEdit = false
                                }
                                .padding(8.dp)
                        )
                        Image(
                            provider = ImageProvider(R.drawable.baseline_send_24),
                            contentDescription = "Send",
                            modifier = GlanceModifier
                                .size(40.dp)
                                .clickable {
                                    sendToWhatsApp(context, mobileNumber)
                                    mobileNumber = ""
                                }
                                .padding(8.dp)
                        )
                        Image(
                            provider = ImageProvider(R.drawable.baseline_send_24),
                            contentDescription = "Send",
                            modifier = GlanceModifier
                                .size(40.dp)
                                .clickable {
                                    sendToTelegram(context, mobileNumber)
                                    mobileNumber = ""
                                }
                                .padding(8.dp)
                        )
                    }
                }
            } else {
                Image(
                    provider = ImageProvider(R.drawable.baseline_send_to_mobile_24),
                    contentDescription = "Send",
                    modifier = GlanceModifier
                        .size(72.dp)
                        .clickable {
                            isEdit = true
                        }
                        .padding(8.dp)
                )
            }
        }
    }

    private fun sendToTelegram(context: Context, mobileNumber: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("tg://openmessage?user_id=$mobileNumber")
            )
                .apply {
                    setPackage("org.telegram.messenger")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
        )
    }

    private fun sendToWhatsApp(context: Context, mobileNumber: String) {
        val message = ""
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://api.whatsapp.com/send?phone=$mobileNumber&text=$message")
            )
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
        )
    }

    @Composable
    fun NumberButton(
        number: String,
        onClick: () -> Unit
    ) {
        Box(
            modifier = GlanceModifier
                .size(40.dp)
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = number)
        }
    }
}

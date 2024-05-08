package dev.vengateshm.kotlin_practice.testing.callbacks_testing

interface NotificationSender {
    fun sendNotification(message: String, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit)
}

class NotificationService(
    private val notificationSender: NotificationSender,
    private val uiHelper: UiHelper
) {
    fun sendNotification(message: String) {
        val formattedMessage = formatMessage(message)
        notificationSender.sendNotification(
            message = formattedMessage,
            onSuccess = {
                uiHelper.updateWindow()
            },
            onFailure = {
                uiHelper.toastErrorMessage(it.localizedMessage)
            }
        )
    }

    private fun formatMessage(message: String): String {
        return "Notification From Vengatesh : $message"
    }
}

class UiHelper {
    fun updateWindow() {
        println("Window updated")
    }

    fun toastErrorMessage(error: String) {
        println("Error : $error")
    }
}

fun main() {

}
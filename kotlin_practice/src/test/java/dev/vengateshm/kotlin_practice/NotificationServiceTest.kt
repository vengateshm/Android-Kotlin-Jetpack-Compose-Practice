package dev.vengateshm.kotlin_practice

import dev.vengateshm.kotlin_practice.testing.callbacks_testing.NotificationSender
import dev.vengateshm.kotlin_practice.testing.callbacks_testing.NotificationService
import dev.vengateshm.kotlin_practice.testing.callbacks_testing.UiHelper
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NotificationServiceTest {
    private val notificationSenderMock: NotificationSender = mockk<NotificationSender>()
    private val uiHelperMock: UiHelper =
        mockk<UiHelper>(relaxed = true) // No behavior with answers for updateWindow(), toastErrorMessage(error: String)
    private val notificationService = NotificationService(notificationSenderMock, uiHelperMock)

    private val onSuccess = slot<() -> Unit>()
    private val onFailure = slot<(Throwable) -> Unit>()

    @BeforeEach
    fun setUp() {
        clearMocks(notificationSenderMock, uiHelperMock)
    }

    @Test
    fun `when formatted notification sending is successful , updateWindow should be called`() {
        every {
            notificationSenderMock.sendNotification(
                any(),
                capture(onSuccess),
                capture(onFailure),
            )
        } answers {
            onSuccess.captured.invoke()
        }

        notificationService.sendNotification("Hola!")

        verify(exactly = 1) { uiHelperMock.updateWindow() }
    }

    @Test
    fun `when formatted notification sending is unsuccessful , toastErrorMessage should be called`() {
        val errorMessage = "Failed to send notification"
        every {
            notificationSenderMock.sendNotification(
                any(),
                capture(onSuccess),
                capture(onFailure),
            )
        } answers {
            onFailure.captured.invoke(Throwable(errorMessage))
        }

        notificationService.sendNotification("Hola!")

        verify(exactly = 1) { uiHelperMock.toastErrorMessage(errorMessage) }
    }
}
package com.app.facepro.faceproschool.ui.notifications

import com.app.facepro.faceproschool.api.ApiService
import com.app.facepro.faceproschool.ui.notifications.model.NotificationRequest
import com.app.facepro.faceproschool.ui.notifications.model.NotificationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepository(private val apiService: ApiService) {
    suspend fun getNotifications(notificationRequest: NotificationRequest): NotificationResponse {
        return withContext(Dispatchers.IO) {
            apiService.getNotifications(notificationRequest)
        }
    }
}

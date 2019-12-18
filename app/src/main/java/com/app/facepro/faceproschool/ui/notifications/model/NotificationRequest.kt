package com.app.facepro.faceproschool.ui.notifications.model

data class NotificationRequest(
    val customer_id: String?,
    val serverkey: String = "serverkey",
    val actioncriteria: String = "2"
)

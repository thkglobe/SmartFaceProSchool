package com.app.facepro.faceproschool.ui.notifications.model

data class NotificationResponse(
    val errorcode: String,
    val message: String,
    val actioncode: String,
    val actionmsg: String,
    val notification_list: List<ActivityData>
)

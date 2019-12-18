package com.app.facepro.faceproschool.ui.notifications.model

import com.google.gson.annotations.SerializedName

data class ActivityData(
    val activityTime: String,
    @SerializedName("activity_message")
    val activityMessage: String,
    @SerializedName("date_time")
    val dateTime: String,
    val id: String,
    val customerId: String,
    val remarks: String,
    @SerializedName("activity_title")
    val activityTitle: String
)

package com.app.facepro.faceproschool.firebase.model

data class FirebaseTokenUpdate(
    val token: String?,
    val customer_id: String,
    val action_criteria: String = "2"
)

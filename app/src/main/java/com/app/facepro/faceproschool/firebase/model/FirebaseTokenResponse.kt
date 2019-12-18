package com.app.facepro.faceproschool.firebase.model

data class FirebaseTokenResponse(
    val errorcode: String,
    val actioncode: String,
    val message: String,
    val actionmsg: String,
    val customer_id: String,
    val gnotifon_key_latest: String,
    val gnotifon_key_previous: String,
    val date: String
)

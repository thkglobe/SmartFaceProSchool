package com.app.facepro.faceproschool.ui.otp.model

data class SendOtpResponse(
    val transactionid: String?,
    val serverusername: String?,
    val serverpassword: String?,
    val actioncriteria: String?,
    val errorcode: String?,
    val actioncode: String?,
    val message: String?,
    val actionmsg: String,
    val mob_num: String,
    val customer_id: String?,
    val otp: String?,
    val otp_message: String
)

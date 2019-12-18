package com.app.facepro.faceproschool.ui.otp.model

data class SendOtpRequest(
    var mob_num: String?, val transactionid: String = "11111111111",
    val serverusername: String = "test_ace",
    val serverpassword: String = "11111111111",
    val actioncriteria: String = "1",
    val req_origin: String = "App"
)

package com.app.facepro.faceproschool.ui.login.model

data class LoginRequest(
    val id: String?,
    val password: String?,
    val notify_token: String?,
    val transactionid: String = "11111111111",
    val serverusername: String = "test_ace",
    val serverpassword: String = "11111111111",
    val action_criteria: String = "2"
)

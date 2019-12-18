package com.app.facepro.faceproschool.ui.otp

import com.app.facepro.faceproschool.api.ApiService
import com.app.facepro.faceproschool.ui.otp.model.SendOtpRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OtpRepository(private val apiService: ApiService) {
    suspend fun sendOtp(otpRequest: SendOtpRequest) = withContext(Dispatchers.IO) {
        apiService.sendOtp(otpRequest)
    }
}

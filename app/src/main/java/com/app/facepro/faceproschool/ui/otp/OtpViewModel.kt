package com.app.facepro.faceproschool.ui.otp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.facepro.faceproschool.common.ErrorHandler
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.ui.otp.model.SendOtpRequest
import kotlinx.coroutines.launch

class OtpViewModel(private val otpRepository: OtpRepository):ViewModel() {
    val isVisibleOTP=MutableLiveData<Boolean>()
    val otpValidateResult = MutableLiveData<Boolean>()
    val otpSendResult = MutableLiveData<Result>()
    val phoneNumber = MutableLiveData<String>()
    val otpNumber=MutableLiveData<String>()
    private var validateOTP: String? = null

    fun validateOtp() {
        otpValidateResult.value = otpNumber.value == validateOTP ?: ""
    }

    fun sendOtp() {
        val otpRequest=SendOtpRequest(phoneNumber.value)
        viewModelScope.launch {
            otpSendResult.value = Result.Progress
            try {
                val data = otpRepository.sendOtp(
                    otpRequest
                )
                if (data.actioncode == "0" && data.errorcode == "0") {
                    otpSendResult.value = Result.Success<Any>(data)
                    validateOTP = data.otp
                    isVisibleOTP.value = true
                } else {
                    otpSendResult.value = Result.Error(data.actionmsg)
                }
            } catch (e: Exception) {
                otpSendResult.value = Result.Failure(ErrorHandler() checkError e)
            }
        }
    }
}

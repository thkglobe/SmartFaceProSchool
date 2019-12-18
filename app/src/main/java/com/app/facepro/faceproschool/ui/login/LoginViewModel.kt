package com.app.facepro.faceproschool.ui.login

import androidx.lifecycle.*
import com.app.facepro.faceproschool.common.ErrorHandler
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_ADDRESS
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_EMAIL_ADDRESS
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_LOGIN
import com.app.facepro.faceproschool.common.PreferenceManager
import com.app.facepro.faceproschool.ui.login.model.LoginRequest
import com.app.facepro.faceproschool.common.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.InstanceIdResult
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_ID
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_LATITUDE
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_LONGITUDE
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_NAME
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_USER_ID
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class LoginViewModel(
    private val loginRepository: LoginRepository, private val preferenceManager: PreferenceManager
) : ViewModel() {
    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginResult = MutableLiveData<Result>()

    fun login(fireBaseToken: String?) {
        viewModelScope.launch {
            loginResult.value = Result.Progress
            try {
                val data = loginRepository.login(
                    LoginRequest(
                        userId.value,
                        password.value,
                        fireBaseToken
                    )
                )
                if (data.actionCode == "0" && data.errorCode == "0") {
                    loginResult.value = Result.Success<Any>(data)
                    preferenceManager.saveInPreference(APP_LOGIN, true)
                    preferenceManager.saveInPreference(APP_CUSTOMER_ID, data.customerId)
                    preferenceManager.saveInPreference(APP_CUSTOMER_NAME, data.customerName)
                    preferenceManager.saveInPreference(APP_CUSTOMER_ADDRESS, data.address)
                    preferenceManager.saveInPreference(APP_CUSTOMER_LATITUDE, data.latitude)
                    preferenceManager.saveInPreference(APP_CUSTOMER_LONGITUDE, data.profilePhoto)
                    preferenceManager.saveInPreference(APP_CUSTOMER_EMAIL_ADDRESS, data.emailId)
                    preferenceManager.saveInPreference(APP_CUSTOMER_USER_ID, userId.value!!)
                } else {
                    loginResult.value = Result.Error(data.actionMsg)
                }
            } catch (e: Exception) {
                loginResult.value = Result.Failure(ErrorHandler() checkError e)
            }
        }
    }
}




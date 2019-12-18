package com.app.facepro.faceproschool.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.facepro.faceproschool.common.ErrorHandler
import com.app.facepro.faceproschool.common.PreferenceConstants
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_ADDRESS
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_EMAIL_ADDRESS
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_LATITUDE
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_LONGITUDE
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_NAME
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_USER_ID
import com.app.facepro.faceproschool.common.PreferenceManager
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.ui.profile.model.ProfileRequest
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel(private val preferenceManager: PreferenceManager, private val profileRepository: ProfileRepository):ViewModel() {
    val profileRequest= MutableLiveData<ProfileRequest>()
    val result= MutableLiveData<Result>()
    val isEditProfile= MutableLiveData<Boolean>()
    val passWord= MutableLiveData<String>()
    val confirmPassWord=MutableLiveData<String>()

    private val customerId = preferenceManager.getFromPreference(PreferenceConstants.APP_CUSTOMER_ID, "")
    private val customerName = preferenceManager.getFromPreference(APP_CUSTOMER_NAME, "")
    private val customerAddress = preferenceManager.getFromPreference(APP_CUSTOMER_ADDRESS, "")
    private val customerLatitude = preferenceManager.getFromPreference(APP_CUSTOMER_LATITUDE, "")
    private val customerLongitude = preferenceManager.getFromPreference(APP_CUSTOMER_LONGITUDE, "")
    private val customerEmailID = preferenceManager.getFromPreference(APP_CUSTOMER_EMAIL_ADDRESS, "")

    init {
        profileRequest.value = ProfileRequest(
            customerId,
            customerName,
            customerAddress,
            customerLatitude,
            customerLongitude,
            customerEmailID, "2"
        )
    }
    fun updateProfileData(){

        viewModelScope.launch {
            result.value = Result.Progress
            try {
                val data=profileRepository.updateProfileInfo(profileRequest.value)
                if (data.actioncode == "0" && data.errorcode == "0") {
                    result.value = Result.Success<Any>(data)
                    preferenceManager.saveInPreference(APP_CUSTOMER_NAME,data.customerName as String)
                    preferenceManager.saveInPreference(APP_CUSTOMER_ADDRESS,data.address as String)
                    preferenceManager.saveInPreference(APP_CUSTOMER_EMAIL_ADDRESS,data.email as String)
                }else {
                    result.value = Result.Error(data.actionmsg)
                }
            } catch (e: Exception) {
                result.value = Result.Failure(ErrorHandler() checkError e)
            }
        }
    }

    fun updatePassword(userId: String?) {
        if(passWord.value.isNullOrEmpty()){
            result.value = Result.Error("please check your password")
            return
        }

        if (passWord.value != confirmPassWord.value) {
            result.value = Result.Error("please check your password")
            return
        }
        profileRequest.value = ProfileRequest(
            customerId,
            customerName,
            customerAddress,
            customerLatitude,
            customerLongitude,
            customerEmailID, "4", passWord.value,userId
        )
        viewModelScope.launch {
            result.value = Result.Progress
            try {
                val data=profileRepository.updateProfileInfo(profileRequest.value)
                if (data.actioncode == "0" && data.errorcode == "0") {
                    result.value = Result.Success<Any>(data)
                    preferenceManager.saveInPreference(APP_CUSTOMER_NAME,data.customerName as String)
                    preferenceManager.saveInPreference(APP_CUSTOMER_ADDRESS,data.address as String)
                    preferenceManager.saveInPreference(APP_CUSTOMER_EMAIL_ADDRESS,data.email as String)
                }else {
                    result.value = Result.Error(data.actionmsg)
                }
            } catch (e: Exception) {
                result.value = Result.Failure(ErrorHandler() checkError e)
            }
        }
    }

    fun clearPreferenceData() {
        preferenceManager.clearData()
    }

    fun resetPasswordPassword() {
        if (passWord.value.isNullOrEmpty()) {
            result.value = Result.Error("please check your password")
            return
        }

        if (passWord.value != confirmPassWord.value) {
            result.value = Result.Error("please check your password")
            return
        }
        profileRequest.value = ProfileRequest(
            customerId,
            customerName,
            customerAddress,
            customerLatitude,
            customerLongitude,
            customerEmailID, "4", passWord.value, preferenceManager.getFromPreference(
                APP_CUSTOMER_USER_ID, ""
            )
        )
        viewModelScope.launch {
            result.value = Result.Progress
            try {
                val data = profileRepository.updateProfileInfo(profileRequest.value)
                if (data.actioncode == "0" && data.errorcode == "0") {
                    result.value = Result.Success<Any>(data)
                    preferenceManager.saveInPreference(
                        APP_CUSTOMER_NAME,
                        data.customerName as String
                    )
                    preferenceManager.saveInPreference(APP_CUSTOMER_ADDRESS, data.address as String)
                    preferenceManager.saveInPreference(
                        APP_CUSTOMER_EMAIL_ADDRESS,
                        data.email as String
                    )
                } else {
                    result.value = Result.Error(data.actionmsg)
                }
            } catch (e: Exception) {
                result.value = Result.Failure(ErrorHandler() checkError e)
            }
        }
    }
}

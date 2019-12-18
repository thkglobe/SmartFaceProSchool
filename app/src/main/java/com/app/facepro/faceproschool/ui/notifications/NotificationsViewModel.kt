package com.app.facepro.faceproschool.ui.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.facepro.faceproschool.common.ErrorHandler
import com.app.facepro.faceproschool.common.PreferenceConstants
import com.app.facepro.faceproschool.common.PreferenceManager
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.ui.notifications.model.NotificationRequest
import kotlinx.coroutines.launch

class NotificationsViewModel(
    private val repository: NotificationRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {
    val result = MutableLiveData<Result>().apply {
        value = Result.Progress
    }

    fun getNotificationData() {
        viewModelScope.launch {
            result.value = Result.Progress
            try{
                val notificationRequest = NotificationRequest(
                    preferenceManager.getFromPreference(
                        PreferenceConstants.APP_CUSTOMER_ID,
                        ""
                    )
                )
                val data=repository.getNotifications(notificationRequest)
                if (data.actioncode == "0" && data.errorcode == "0") {
                    result.value =
                        Result.Success<Any>(data)
                }

            }catch (e:Exception){
                result.value = Result.Failure(ErrorHandler().checkError(e))
            }
        }
    }
}

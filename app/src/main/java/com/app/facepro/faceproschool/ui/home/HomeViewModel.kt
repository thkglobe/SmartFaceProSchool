package com.app.facepro.faceproschool.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.StringUtil
import com.app.facepro.faceproschool.common.ErrorHandler
import com.app.facepro.faceproschool.common.PreferenceConstants
import com.app.facepro.faceproschool.common.PreferenceConstants.Companion.APP_CUSTOMER_ID
import com.app.facepro.faceproschool.common.PreferenceManager
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.ui.home.model.*
import com.app.facepro.faceproschool.utils.getDateDAY
import com.app.facepro.faceproschool.utils.getDateMonth
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val homeRepository: HomeRepository,private val preferenceManager: PreferenceManager) : ViewModel() {
    val uiState = MutableLiveData<Result>().apply {
        value = Result.Progress
    }
    val pan1MsgDetailsItem = MutableLiveData<Pan1MsgDetailsItem>()
    val pan2MsgDetailsItem = MutableLiveData<Pan2MsgDetailsItem>()
    val pan3MsgDetailsItem = MutableLiveData<List<Pan3MsgDetailsItem?>>()
    val pan4MsgDetailsItem = MutableLiveData<Pan4MsgDetails>()
    val countUpdateItem = MutableLiveData<Int>()
    val dateDay= getDateDAY()
    val dateMonth= getDateMonth()
    fun fetchHomeData() {
        viewModelScope.launch {
            uiState.value = Result.Progress
            try {
                val data = homeRepository.fetchHomeData(
                    HomeRequest(preferenceManager.getFromPreference(APP_CUSTOMER_ID, ""))
                )
                if (data.actioncode == "0" && data.errorcode == "0") {
                    uiState.value = Result.Success(data)
                    data.pan1MsgDetails?.let {
                        pan1MsgDetailsItem.value = it[0]
                    }
                    data.pan2MsgDetails?.let {
                        pan2MsgDetailsItem.value = it[0]
                    }
                    data.pan4MsgDetails?.let {
                        pan4MsgDetailsItem.value =it[0]
                    }
                    pan3MsgDetailsItem.value = data.pan3MsgDetails
                    countUpdateItem.value= data.pan1MsgDetails?.get(0)?.pan1ActnotifyCount?:0
                } else {
                    uiState.value = Result.Error(data.actionmsg)
                }
            } catch (e: Exception) {
                uiState.value = Result.Failure(ErrorHandler() checkError e)
            }
        }
    }
}

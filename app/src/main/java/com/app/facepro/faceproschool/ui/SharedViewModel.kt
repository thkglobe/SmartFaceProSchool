package com.app.facepro.faceproschool.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thkglobe.app.facepro.common.MenuVisibility

class SharedViewModel:ViewModel() {
    val toolBarVisibilityObserver = MutableLiveData<MenuVisibility>()
    val notificationCountObserver = MutableLiveData<String>()

    fun visibleToolbar(menuVisibility: MenuVisibility) {
        toolBarVisibilityObserver.value=menuVisibility
    }
    fun notificationCount(count:String) {
        notificationCountObserver.value=count
    }


}



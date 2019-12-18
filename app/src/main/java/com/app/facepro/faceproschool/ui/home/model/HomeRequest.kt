package com.app.facepro.faceproschool.ui.home.model

import com.google.gson.annotations.SerializedName

data class HomeRequest(@SerializedName("customer_id") val customerId: String)

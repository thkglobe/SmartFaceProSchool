package com.app.facepro.faceproschool.ui.profile.model

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
    @SerializedName("customer_id")
    val customerId: String,
    @SerializedName("customer_name")
    var customerName: String,
    var address: String,
    var lattitude: String,
    var longitutde: String,
    var email:String,
    @SerializedName("action_criteria")
    val actionCriteria: String,
    val password: String? = null,
    @SerializedName("user_id")
    val userId: String? = null,
    val serverusername: String = "test_ace",
    val transactionid: String = "test_ace",
    @SerializedName("user_code")
    val userCode: String? = null,
    @SerializedName("loc_name")
    val locName: String? = null,
    @SerializedName("org_code")
    val orgCode: String = "DHMDMSTEMP",
    val serverpassword: String = "1111",
    @SerializedName("store_id")
    val storeId: String? = "Store",
    val level: String = "3"
)

package com.app.facepro.faceproschool.ui.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("errorcode")
    val errorCode: String,
    @SerializedName("actioncode")
    val actionCode: String,
    val message: String,
    @SerializedName("actionmsg")
    val actionMsg: String,
    @SerializedName("customer_id")
    val customerId: String,
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val emailId: String,
    @SerializedName("profile_photo")
    val profilePhoto: String,
    @SerializedName("longitutde")
    val longitude: String,
    @SerializedName("lattitude")
    val latitude: String
)

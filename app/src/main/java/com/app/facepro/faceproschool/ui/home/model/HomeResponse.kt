package com.app.facepro.faceproschool.ui.home.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("pan2_msg_avaiable")
    val pan2MsgAvaiable: String? = null,
    @SerializedName("pan3_msg_avaiable")
    val pan3MsgAvaiable: String? = null,
    @SerializedName("pan1_msg_details")
    val pan1MsgDetails: List<Pan1MsgDetailsItem?>? = null,
    @SerializedName("pan2_msg_details")
    val pan2MsgDetails: List<Pan2MsgDetailsItem?>? = null,
    val actionmsg: String,
    val actioncode: String,
    val message: String? = null,
    @SerializedName("pan3_msg_details")
    val pan3MsgDetails: List<Pan3MsgDetailsItem?>? = null,
    @SerializedName("pan1_msg_avaiable")
    val pan1MsgAvaiable: String? = null,
    val errorcode: String
)

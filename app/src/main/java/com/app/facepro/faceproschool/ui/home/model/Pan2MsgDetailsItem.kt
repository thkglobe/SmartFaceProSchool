package com.app.facepro.faceproschool.ui.home.model

import com.google.gson.annotations.SerializedName

data class Pan2MsgDetailsItem(
	@SerializedName("pan2_total_leave")
	val pan2TotalLeave: String? = null,
	@SerializedName("pan2_attendenc_count")
	val pan2AttendencCount: String? = null,
	@SerializedName("pan2_attendenc_details")
	val pan2AttendencDetails: String? = null
)

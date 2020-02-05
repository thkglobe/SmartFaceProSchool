package com.app.facepro.faceproschool.ui.home.model

import com.google.gson.annotations.SerializedName

data class Pan3MsgDetailsItem(
	@SerializedName("pan3_details")
	val pan3Details: String? = null,
	@SerializedName("pan3_img")
	val pan3Image: String? = null
)

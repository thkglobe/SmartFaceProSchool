package com.app.facepro.faceproschool.ui.home.model

import com.google.gson.annotations.SerializedName

data class Pan1MsgDetailsItem(
	@SerializedName("pan1_actnotify_msg")
	val pan1ActnotifyMsg: String? = null,
	@SerializedName("pan1_actnotify_count")
	val pan1ActnotifyCount: Int = 0
)

package com.app.facepro.faceproschool.ui.profile.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
	val accountNumber: String,
	val language: String? = null,
	val serverpassword: String? = null,
	val actioncode: String? = null,
	val transactionid: String? = null,
	val actioncriteria: String? = null,
	val password: String? = null,
	val userCode: String,
	val longitutde: String,
	val actionmsg: String,
	val locName: String,
	val pan: String? = null,
	val enrollDate: String? = null,
	val fld3: String? = null,
	val errorcode: String? = null,
	val email: String? = null,
	val fld2: String? = null,
	val fld1: String? = null,
	val address: String? = null,
	val lattitude: String? = null,
	val landPhoneNo: String? = null,
	val level: String? = null,
	val serverusername: String? = null,
	val message: String? = null,
	val tranCustId: String? = null,
	val userId: String? = null,
	@SerializedName("customer_name")
	val customerName: String? = null,
	val passWrongCount: String? = null,
	val customerId: String? = null,
	val status: String? = null
)

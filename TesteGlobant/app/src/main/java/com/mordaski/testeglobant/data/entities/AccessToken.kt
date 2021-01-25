package com.mordaski.testeglobant.data.entities

import com.google.gson.annotations.SerializedName

data class AccessToken(
	@SerializedName("token")
	var Token: String?
	)
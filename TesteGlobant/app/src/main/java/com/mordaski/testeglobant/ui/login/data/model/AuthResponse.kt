package com.mordaski.testeglobant.ui.login.data.model

import com.google.gson.annotations.SerializedName

class AuthResponse {
    @SerializedName("token")
    var token: String? = null
}
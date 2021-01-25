package com.mordaski.testeglobant.ui.login.data.model

import com.google.gson.annotations.SerializedName

class AuthModel {
    @SerializedName("user")
    var usuario: String? = null
    @SerializedName("pass")
    var password: String? = null
}
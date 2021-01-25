package com.mordaski.testeglobant.data.remote

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("dateTime")
    var dateTime: String? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("messageError")
    var messageError: String? = null
    @SerializedName("status")
    var status: Int? = null
}
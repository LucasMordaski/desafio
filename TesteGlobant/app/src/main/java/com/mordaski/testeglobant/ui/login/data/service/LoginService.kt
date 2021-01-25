package com.mordaski.testeglobant.ui.login.data.service

import com.mordaski.testeglobant.ui.login.data.model.AuthModel
import com.mordaski.testeglobant.ui.login.data.model.AuthResponse
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @POST("login")
    suspend fun doLogin(@Body auth: AuthModel): Response<AuthResponse>
}
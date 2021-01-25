package com.mordaski.testeglobant.ui.login.data.mock

import com.mordaski.testeglobant.ui.login.data.service.LoginService
import com.mordaski.testeglobant.ui.login.data.model.AuthModel
import com.mordaski.testeglobant.ui.login.data.model.AuthResponse
import retrofit2.Response

class LoginImpMock : LoginService {
     override suspend fun doLogin(auth: AuthModel): Response<AuthResponse> {
        return Response.success(AuthResponse())
    }

}
package com.mordaski.testeglobant.ui.login.data

import com.mordaski.testeglobant.data.remote.BaseModule
import com.mordaski.testeglobant.ui.login.data.model.AuthModel
import com.mordaski.testeglobant.ui.login.data.model.AuthResponse
import com.mordaski.testeglobant.ui.login.data.service.LoginService
import com.mordaski.testeglobant.utils.Resource

class LoginImpl(val service: LoginService) : LoginUseCase() {

    override suspend fun execute(params: AuthModel?, token: String?): Resource<AuthResponse> {
        return try {
            params?.let {
                BaseModule().safeApiCall {
                    service.doLogin(it)
                }
            } ?: Resource.error(ExceptionError())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }


}
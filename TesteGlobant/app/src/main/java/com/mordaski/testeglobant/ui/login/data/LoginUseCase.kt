package com.mordaski.testeglobant.ui.login.data


import com.mordaski.testeglobant.data.entities.AccessToken
import com.mordaski.testeglobant.ui.login.data.model.AuthModel
import com.mordaski.testeglobant.ui.login.data.model.AuthResponse
import com.mordaski.testeglobant.utils.UseCase
import com.mordaski.testeglobant.utils.Resource

abstract class LoginUseCase : UseCase<AuthModel, String?, Resource<AuthResponse>>() {
    class ExceptionError : Exception()
}
package com.mordaski.testeglobant.ui.login

import androidx.lifecycle.ViewModel
import com.mordaski.testeglobant.ui.login.data.LoginUseCase
import com.mordaski.testeglobant.ui.login.data.model.AuthModel
import com.mordaski.testeglobant.ui.login.data.model.AuthResponse
import com.mordaski.testeglobant.utils.Resource
import com.mordaski.testeglobant.utils.SingleLiveEvent

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

    var auth = AuthModel()
    val response = SingleLiveEvent<Resource<AuthResponse>>()

    suspend fun doLogin() {
        response.value = Resource.loading(null)

        auth.usuario = "devmobile"
        auth.password = "uC&+}H4wg?rYbF:"

          val resource = try {
               useCase(auth, null)
          } catch (error: Throwable) {
               Resource.error(error)
          }
          response.value = resource
    }

}

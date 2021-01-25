package com.mordaski.testeglobant.data.interactor

import androidx.lifecycle.MutableLiveData
import com.mordaski.testeglobant.data.entities.AccessToken
import com.mordaski.testeglobant.utils.SingleLiveEvent

interface AuthenticationManager {
	fun token(): AccessToken?

	//todo any
	fun refreshToken(credentials: Any): AccessToken?

	fun isAuthenticated(): Boolean

	fun observeAuthentication(): MutableLiveData<String?>

	fun saveToken(token: AccessToken)

	//todo any
	fun getCredentials(): Any

	fun getDevice(): String?


	//todo any
	fun initToken(credentials: Any, toObserve: SingleLiveEvent<Any?>)
}
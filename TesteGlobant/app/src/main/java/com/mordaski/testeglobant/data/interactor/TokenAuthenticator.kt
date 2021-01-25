package com.mordaski.testeglobant.data.interactor

import okhttp3.*

class TokenAuthenticator : Authenticator, Interceptor {

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
                return response.request()
                        .newBuilder()
                        .build()
            }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .build()

        return chain.proceed(request)
    }
}
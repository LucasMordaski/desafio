package com.mordaski.testeglobant.data.remote

import com.google.gson.Gson
import com.mordaski.testeglobant.utils.Resource
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseModule {

    class UnknowHostException : Exception()
    class TimeOutException : Exception()
    class NoInternetException : Exception()
    class Unauthorized : Exception()
    class InvalidData : Exception()
    class ResponseException(errorMessage: String?) : Exception(errorMessage)

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource<T>? {
        return safeApiResult(call)
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) Resource.success(response.body()!!)
            else {
                return when {
                    response.code() == 401 -> Resource.error(Unauthorized())
                    response.code() == 412 -> Resource.error(InvalidData())
                    else -> {
                        val baseRequest = Gson().fromJson(response.errorBody().toString(), BaseResponse::class.java)
                        Resource.error(ResponseException(baseRequest.messageError))
                    }
                }
            }

        } catch (ue1: UnknownHostException) {
            return Resource.error(UnknowHostException())
        } catch (se3: SocketTimeoutException) {
            return Resource.error(TimeOutException())
        } catch (e2: Exception) {
            return Resource.error(e2)
        } catch (e: Throwable) {
            return Resource.error(NoInternetException())
        }
    }
}
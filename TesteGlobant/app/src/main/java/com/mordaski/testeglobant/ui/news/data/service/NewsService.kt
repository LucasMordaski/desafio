package com.mordaski.testeglobant.ui.news.data.service

import com.mordaski.testeglobant.ui.news.data.model.NewsDetailsResponse
import com.mordaski.testeglobant.ui.news.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NewsService {

    @GET("news")
    suspend fun getAllnews(@Header("Authorization")token: String) : Response<List<NewsResponse>>

    @GET("news/{id}")
    suspend fun getNews(@Header("Authorization") token: String, @Path("id") id: String): Response<List<NewsDetailsResponse>>
}
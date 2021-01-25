package com.mordaski.testeglobant.ui.news.data

import com.mordaski.testeglobant.data.remote.BaseModule
import com.mordaski.testeglobant.ui.news.data.model.NewsDetailsResponse
import com.mordaski.testeglobant.ui.news.data.model.NewsResponse
import com.mordaski.testeglobant.ui.news.data.service.NewsService
import com.mordaski.testeglobant.utils.Resource

class ListNewsImp(val service: NewsService) : ListNewsUseCase() {

    override suspend fun execute(params: Int?, token: String?): Resource<List<NewsResponse>> {
        return try {
            token?.let {
                BaseModule().safeApiCall {
                    service.getAllnews("Bearer "+token)
                } ?: Resource.error(ExceptionError())
            }?: Resource.error(ExceptionError())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}
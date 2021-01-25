package com.mordaski.testeglobant.ui.news.data

import com.mordaski.testeglobant.data.remote.BaseModule
import com.mordaski.testeglobant.ui.news.data.model.NewsDetailsResponse
import com.mordaski.testeglobant.ui.news.data.service.NewsService
import com.mordaski.testeglobant.utils.Resource

class NewsImp(val service: NewsService) : NewsUseCase() {

    override suspend fun execute(params: String?, token: String?): Resource<List<NewsDetailsResponse>> {
        return try {
            token?.let {
                params?.let {
                    BaseModule().safeApiCall {
                        service.getNews("Bearer "+token,params)
                    } ?: Resource.error(ListNewsUseCase.ExceptionError())
                }?: Resource.error(ListNewsUseCase.ExceptionError())
            }?: Resource.error(ListNewsUseCase.ExceptionError())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }


}
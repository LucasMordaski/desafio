package com.mordaski.testeglobant.ui.news.data


import com.mordaski.testeglobant.ui.news.data.model.NewsDetailsResponse
import com.mordaski.testeglobant.utils.UseCase
import com.mordaski.testeglobant.utils.Resource

abstract class NewsUseCase : UseCase<String?, String?, Resource<List<NewsDetailsResponse>>>() {
    class ExceptionError : Exception()
}
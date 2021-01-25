package com.mordaski.testeglobant.ui.news.data


import com.mordaski.testeglobant.ui.news.data.model.NewsResponse
import com.mordaski.testeglobant.utils.UseCase
import com.mordaski.testeglobant.utils.Resource

abstract class ListNewsUseCase : UseCase<Int?, String?, Resource<List<NewsResponse>>>() {
    class ExceptionError : Exception()
}
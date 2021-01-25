package com.mordaski.testeglobant.ui.news

import androidx.lifecycle.ViewModel
import com.mordaski.testeglobant.ui.news.data.ListNewsUseCase
import com.mordaski.testeglobant.ui.news.data.model.NewsDetailsResponse
import com.mordaski.testeglobant.ui.news.data.model.NewsResponse
import com.mordaski.testeglobant.utils.Resource
import com.mordaski.testeglobant.utils.SingleLiveEvent

class NewsViewModel(private val useCase: ListNewsUseCase) : ViewModel() {

    lateinit var token: String
    val response = SingleLiveEvent<Resource<List<NewsResponse>>>()

    suspend fun getNews() {
        response.value = Resource.loading(null)

        val resource = try {
            useCase.invoke(null, token)
        } catch (error: Throwable) {
            Resource.error(error)
        }

        response.value = resource
    }


}

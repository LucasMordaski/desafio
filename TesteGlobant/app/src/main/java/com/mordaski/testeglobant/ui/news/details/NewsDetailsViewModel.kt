package com.mordaski.testeglobant.ui.news.details

import androidx.lifecycle.ViewModel
import com.mordaski.testeglobant.ui.news.data.NewsUseCase
import com.mordaski.testeglobant.ui.news.data.model.NewsDetailsResponse
import com.mordaski.testeglobant.utils.Resource
import com.mordaski.testeglobant.utils.SingleLiveEvent

class NewsDetailsViewModel(private val useCase: NewsUseCase) : ViewModel() {

    lateinit var token: String
    val response = SingleLiveEvent<Resource<List<NewsDetailsResponse>>>()

    suspend fun getNews(id: String) {
        response.value = Resource.loading(null)

        val resource = try {
            useCase.invoke(id, token)
        } catch (error: Throwable) {
            Resource.error(error)
        }

        response.value = resource
    }


}

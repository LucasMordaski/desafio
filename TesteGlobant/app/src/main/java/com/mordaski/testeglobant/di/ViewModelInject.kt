package com.mordaski.testeglobant.di

import com.mordaski.testeglobant.ui.login.LoginViewModel
import com.mordaski.testeglobant.ui.login.data.LoginUseCase
import com.mordaski.testeglobant.ui.news.NewsViewModel
import com.mordaski.testeglobant.ui.news.data.ListNewsUseCase
import com.mordaski.testeglobant.ui.news.data.NewsUseCase
import com.mordaski.testeglobant.ui.news.details.NewsDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModules: Module = module {

    viewModel { LoginViewModel(get() as LoginUseCase) }
    viewModel { NewsViewModel(get() as ListNewsUseCase) }
    viewModel { NewsDetailsViewModel(get() as NewsUseCase) }

}
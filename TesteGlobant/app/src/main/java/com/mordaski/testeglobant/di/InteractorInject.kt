package com.mordaski.testeglobant.di

import com.mordaski.testeglobant.ui.login.data.LoginImpl
import com.mordaski.testeglobant.ui.login.data.LoginUseCase
import com.mordaski.testeglobant.ui.news.data.ListNewsImp
import com.mordaski.testeglobant.ui.news.data.ListNewsUseCase
import com.mordaski.testeglobant.ui.news.data.NewsImp
import com.mordaski.testeglobant.ui.news.data.NewsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val dependenciesModule: Module = module {

    single { LoginImpl(get()) as LoginUseCase }
    single { ListNewsImp(get()) as ListNewsUseCase }
    single { NewsImp(get()) as NewsUseCase }

}

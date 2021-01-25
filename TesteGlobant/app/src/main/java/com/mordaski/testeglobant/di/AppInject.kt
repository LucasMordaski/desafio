package com.mordaski.testeglobant.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
object  AppInject{

    fun modules(): List<Module> = listOf(
            mockServices,
            dependenciesModule,
            viewModelModules)
}
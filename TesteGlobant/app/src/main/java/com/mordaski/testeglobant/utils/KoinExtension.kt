package com.mordaski.testeglobant.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import retrofit2.Retrofit

inline fun <reified T> Scope.resolveRetrofit(): T {
    val retrofit: Retrofit = get()
    return retrofit.create(T::class.java)
}

inline fun <reified T : ViewModel> NavController.viewModel(navGraphId: Int): T {
    val storeOwner = getViewModelStoreOwner(navGraphId)
    return ViewModelProvider(storeOwner)[T::class.java]
}

fun List<Module>.loadModules() {
    unloadKoinModules(this)
    loadKoinModules(this)
}
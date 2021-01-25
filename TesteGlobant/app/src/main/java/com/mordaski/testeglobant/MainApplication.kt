package com.mordaski.testeglobant

import android.app.Application
import android.content.Context
import com.mordaski.testeglobant.di.*
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class MainApplication : Application() {

    private var context: Context? = null


    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(AppInject.modules())
        }
    }


    fun getContext(): Context? {
        return context
    }

}
package com.mordaski.testeglobant.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.original.bank.novajornada.di.RemoteModule
import com.mordaski.testeglobant.R
import com.mordaski.testeglobant.utils.loadModules
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf


@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    val modules: List<Module> by lazy {
        listOf(
            RemoteModule.networkInject,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modules.loadModules()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}

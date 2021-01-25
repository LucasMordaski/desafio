package com.mordaski.testeglobant.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in T, in O, out U> : CoroutineScope, KoinComponent {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    abstract suspend fun execute(params: T?, token: O?) : U

    suspend operator fun invoke(params: T?, token: O?): U {
        return execute(params, token)
    }
}
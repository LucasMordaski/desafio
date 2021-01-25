package br.com.original.bank.novajornada.di
import com.mordaski.testeglobant.BuildConfig
import com.mordaski.testeglobant.data.interactor.TokenAuthenticator
import com.mordaski.testeglobant.data.remote.BaseModule
import com.mordaski.testeglobant.ui.login.data.service.LoginService
import com.mordaski.testeglobant.ui.news.data.service.NewsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteModule {

    companion object {

        const val TIMEOUT: Long = 60000

        val networkInject = module {

            single { TokenAuthenticator() }

            single { BaseModule() }

            factory { createOkHttpClient(get()) }

            factory { createRetrofit(get()) }

            factory { get<Retrofit>().create(LoginService::class.java) }
            factory { get<Retrofit>().create(NewsService::class.java) }

        }

        private fun createOkHttpClient(tokenAuthenticator: TokenAuthenticator):
                OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(tokenAuthenticator)
                .authenticator(tokenAuthenticator)
                .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .also {
                    if (BuildConfig.DEBUG)
                        it.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
                .build()

        private fun createRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
                .baseUrl("https://teste-dev-mobile-api.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}

package com.deneb.newsapp.core.di

import android.content.Context
import android.content.SharedPreferences
import com.deneb.newsapp.BuildConfig
import com.deneb.newsapp.core.extensions.SharedPrefences
import com.deneb.newsapp.core.platform.ContextHandler
import com.deneb.newsapp.core.platform.NetworkHandler
import com.deneb.newsapp.features.news.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { ContextHandler(get()) }
    factory { NetworkHandler(get()) }
    single {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Si necesitamos el builder para proporcionarle otra urlbase
    single {
        Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
    }
}
val applicationModule = module(override = true) {
    /*scope(named<ArticlesFragment>()){
        factory { ArticleAdapter() }
    }*/
    factory { ArticleAdapter() }
    single<SharedPreferences> { androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE) }
}

val repositoryModule = module {
    factory<ArticlesRepository> { ArticlesRepository.Network(get(), get(), get(), get(), get()) }
}

val useCaseModule = module {
    factory { GetArticles(get()) }
    factory { GetArticlesFlow(get()) }
}

val dataSourceModule = module {
    factory { ArticlesService(get()) }
}

val databaseModule = module {
    factory { FetchLocal(get()) }
    factory { ArticlesLocal(get()) }
}

val viewModelModule = module {
    viewModel {
        GetArticlesViewModel(get())
    }
    /*scope(named<ArticlesFragment>()){
        viewModel {
            GetArticlesViewModel(get())
        }
    }*/
}

private fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}

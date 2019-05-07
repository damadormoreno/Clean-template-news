package com.deneb.newsapp.core.di

import com.deneb.newsapp.BuildConfig
import com.deneb.newsapp.core.navigation.Navigator
import com.deneb.newsapp.core.platform.ContextHandler
import com.deneb.newsapp.core.platform.NetworkHandler
import com.deneb.newsapp.features.news.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val applicationModule = module(override = true) {
    single {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
    }
    single { Navigator() }
    factory { ArticleAdapter() }
    factory { ContextHandler(get()) }
    factory { FetchLocal(get()) }
    factory { ArticlesLocal(get()) }
    factory { ArticlesService(get()) }
    factory { NetworkHandler(get()) }
    factory<ArticlesRepository> { ArticlesRepository.Network(get(), get(), get(), get()) }
    factory { GetArticles(get()) }
    viewModel {
        GetArticlesViewModel(get())
    }

}

private fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}
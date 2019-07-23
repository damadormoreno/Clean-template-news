package com.deneb.newsapp.core.di

import com.deneb.newsapp.BuildConfig
import com.deneb.newsapp.core.navigation.Navigator
import com.deneb.newsapp.core.platform.ContextHandler
import com.deneb.newsapp.core.platform.NetworkHandler
import com.deneb.newsapp.features.news.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO: Mirar scopes para no inyectar en toda la aplicación
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
    single {
        Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
    }
}
val applicationModule = module(override = true) {
    single { Navigator() }
    factory { ArticleAdapter() }
}

val databaseModule = module {
    factory { FetchLocal(get()) }
    factory { ArticlesLocal(get()) }
}

val datasourceModule = module {
    factory { ArticlesService(get()) }
}

val repositoryModule = module {
    factory<ArticlesRepository> { ArticlesRepository.Network(get(), get(), get(), get()) }
}
val useCaseModule = module {
    factory { GetArticles(get()) }
}

val viewModelModule = module {
    viewModel { GetArticlesViewModel(get()) }
}

private fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}
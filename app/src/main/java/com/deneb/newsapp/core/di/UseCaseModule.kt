package com.deneb.newsapp.core.di

import com.deneb.newsapp.features.news.GetArticles
import com.deneb.newsapp.features.news.GetArticlesFlow
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetArticles(get()) }
    factory { GetArticlesFlow(get(), Dispatchers.Default) }
}
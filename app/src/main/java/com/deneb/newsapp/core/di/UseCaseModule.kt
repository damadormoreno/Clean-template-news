package com.deneb.newsapp.core.di

import com.deneb.newsapp.features.news.GetArticles
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetArticles(get()) }
}
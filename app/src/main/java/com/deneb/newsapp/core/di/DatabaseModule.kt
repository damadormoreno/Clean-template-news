package com.deneb.newsapp.core.di

import com.deneb.newsapp.features.news.ArticlesLocal
import com.deneb.newsapp.features.news.FetchLocal
import org.koin.dsl.module

val databaseModule = module {
    factory { FetchLocal(get()) }
    factory { ArticlesLocal(get()) }
}
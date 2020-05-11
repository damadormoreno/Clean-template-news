package com.deneb.newsapp.core.di

import com.deneb.newsapp.features.news.ArticlesService
import org.koin.dsl.module

val dataSourceModule = module {
    factory { ArticlesService(get()) }
}
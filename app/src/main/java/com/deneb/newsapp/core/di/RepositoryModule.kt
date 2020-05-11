package com.deneb.newsapp.core.di

import com.deneb.newsapp.features.news.ArticlesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ArticlesRepository> { ArticlesRepository.Network(get(), get(), get(), get(), get()) }
}
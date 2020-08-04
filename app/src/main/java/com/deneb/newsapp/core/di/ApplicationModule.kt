package com.deneb.newsapp.core.di

import android.content.Context
import android.content.SharedPreferences
import com.deneb.newsapp.features.news.ArticleAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module(override = true) {
    /*scope(named<ArticlesFragment>()){
        factory { ArticleAdapter() }
    }*/
    factory { ArticleAdapter() }
    single<SharedPreferences> { androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE) }
}
package com.deneb.newsapp.core.di

import com.deneb.newsapp.features.news.GetArticlesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


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

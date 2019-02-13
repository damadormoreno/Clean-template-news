package com.deneb.newsapp.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.deneb.newsapp.features.news.GetArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // =============================================================================================
    @Binds
    @IntoMap
    @ViewModelKey(GetArticlesViewModel::class)
    abstract fun bindsArticlesViewModel(getArticlesViewModel: GetArticlesViewModel): ViewModel
}
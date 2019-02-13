package com.deneb.newsapp.core.di

import com.deneb.newsapp.AndroidApplication
import com.deneb.newsapp.core.di.viewmodel.ViewModelModule
import com.deneb.newsapp.features.news.ArticlesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class
    , ViewModelModule::class
])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)

    fun inject(articlesFragment: ArticlesFragment)
}
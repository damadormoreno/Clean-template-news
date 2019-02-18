package com.deneb.newsapp.features.news

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesService
(retrofit: Retrofit) : ArticlesApi {
    private val articlesApi by lazy { retrofit.create(ArticlesApi::class.java) }

    override fun getArticles(): Call<NewsEntity> {
        return articlesApi.getArticles()
    }
}
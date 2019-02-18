package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.dataBase.AppDatabase
import com.deneb.newsapp.core.platform.ContextHandler
import javax.inject.Inject
import javax.inject.Singleton

class ArticlesLocal
(contextHandler: ContextHandler): ArticlesDBLocal{

    private val articlesApi by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.articleEntityDao()!!
    }

    override fun getArticles(): List<ArticleEntity> = articlesApi.getArticles()
    override fun addArticle(articleEntity: ArticleEntity) = articlesApi.insertArticle(articleEntity)

}
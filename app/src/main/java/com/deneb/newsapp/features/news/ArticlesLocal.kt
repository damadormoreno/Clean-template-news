package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.dataBase.AppDatabase
import com.deneb.newsapp.core.platform.ContextHandler
import org.koin.java.KoinJavaComponent.inject
import javax.inject.Inject
import javax.inject.Singleton

class ArticlesLocal
(contextHandler: ContextHandler, appDatabase: AppDatabase): ArticlesDBLocal{

    private val articlesApi by lazy {
        appDatabase.articleEntityDao()
    }

    override fun getArticles(): List<ArticleEntity> = articlesApi.getArticles()
    override fun addArticle(articleEntity: ArticleEntity) = articlesApi.insertArticle(articleEntity)

}
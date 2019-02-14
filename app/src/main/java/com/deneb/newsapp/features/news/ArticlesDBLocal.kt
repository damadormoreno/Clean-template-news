package com.deneb.newsapp.features.news

interface ArticlesDBLocal {
    fun getArticles(): List<ArticleEntity>
    fun addArticle(articleEntity: ArticleEntity): Any
}
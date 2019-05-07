package com.deneb.newsapp.features.news

import androidx.lifecycle.MutableLiveData
import com.deneb.newsapp.core.platform.BaseViewModel
import javax.inject.Inject

class GetArticlesViewModel
(private val getArticles: GetArticles): BaseViewModel() {

    var articles: MutableLiveData<List<ArticleView>> = MutableLiveData()
    var articlesViews: List<ArticleView> = listOf()

    fun getArticles() = getArticles.invoke(
        GetArticles.Params()) {
            it.either(::handleFailure, ::handleArticlesList)
        }

    private fun handleArticlesList(articles: List<Article>) {
        articlesViews = articles.map {
            it.toArticleView()
        }
        this.articles.value = articlesViews
    }

    fun filter(string: String) {
        val articlesTempList : MutableList<ArticleView> = mutableListOf()
        for (article in articlesViews) {
            if (article.title.toLowerCase().contains(string.toLowerCase())) {
                articlesTempList.add(article)
            }
        }
        this.articles.value = articlesTempList
    }

}
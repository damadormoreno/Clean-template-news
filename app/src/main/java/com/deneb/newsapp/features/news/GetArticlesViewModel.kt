package com.deneb.newsapp.features.news

import androidx.lifecycle.MutableLiveData
import com.deneb.newsapp.core.functional.map
import com.deneb.newsapp.core.interactor.UseCaseFlow
import com.deneb.newsapp.core.platform.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.util.*

@ExperimentalCoroutinesApi
class GetArticlesViewModel
    (
    private val getArticles: GetArticlesFlow
) : BaseViewModel() {

    var articles: MutableLiveData<List<ArticleView>> = MutableLiveData()
    private var articlesViews: List<ArticleView> = listOf()
    var loading: MutableLiveData<Boolean> = MutableLiveData()


    suspend fun getArticles() {
        getArticles.invoke(Any())
            .onStart { loading.value = true }
            .onEach { loading.value = false }
            .map { either ->
                either.map { list ->
                    list.map { article ->
                        article.toArticleView()
                    }
                }
            }
            .collect {
                it.fold(::handleFailure, ::handleArticlesList)
            }
    }

    private fun handleArticlesList(articles: List<ArticleView>) {
        this.articles.value = articles
        articlesViews = articles
    }

    fun filter(string: String) {
        val articlesTempList: MutableList<ArticleView> = mutableListOf()
        for (article in articlesViews) {
            if (article.title.toLowerCase(Locale.getDefault())
                    .contains(string.toLowerCase(Locale.getDefault()))
            ) {
                articlesTempList.add(article)
            }
        }
        this.articles.value = articlesTempList
    }

}
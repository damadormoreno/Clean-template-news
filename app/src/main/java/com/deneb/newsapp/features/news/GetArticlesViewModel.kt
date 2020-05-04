package com.deneb.newsapp.features.news

import androidx.lifecycle.MutableLiveData
import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.functional.State
import com.deneb.newsapp.core.interactor.UseCaseFlow
import com.deneb.newsapp.core.platform.BaseViewModel
import kotlinx.coroutines.flow.collect
import java.util.*

class GetArticlesViewModel
    (private val getArticles: GetArticlesFlow) : BaseViewModel() {

    var articles: MutableLiveData<List<ArticleView>> = MutableLiveData()
    var articlesViews: List<ArticleView> = listOf()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun getArticlesFlow() {
        getArticles.invoke(UseCaseFlow.None())
            .collect { state ->
            when(state) {
                is State.Loading -> loading.value = true //ShowLoading, un livedata?
                is State.Failed -> {
                    loading.value = false
                    handleFailure(state.failure)
                }
                is State.Success -> handleArticlesList(state.data)
            }
        }
    }

    private fun handleArticlesList(articles: List<Article>) {
        loading.value = false
        articlesViews = articles.map {
            it.toArticleView()
        }
        this.articles.value = articlesViews
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
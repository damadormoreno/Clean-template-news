package com.deneb.newsapp.features.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deneb.newsapp.core.extensions.cancelIfActive
import com.deneb.newsapp.core.functional.Error
import com.deneb.newsapp.core.functional.Success
import com.deneb.newsapp.core.interactor.UseCaseFlow
import com.deneb.newsapp.core.platform.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class GetArticlesViewModel
    (private val getArticles: GetArticlesFlow) : BaseViewModel() {

    var articles: MutableLiveData<List<ArticleView>> = MutableLiveData()
    var articlesViews: List<ArticleView> = listOf()

    var job: Job? = null

    fun getArticles() {
        job.cancelIfActive()
        job = viewModelScope.launch {
            getArticles(UseCaseFlow.None())
                .onStart {
                    // Invokes the given [action] when the this flow starts to be collected.
                    //lanzar progressdialog xejemplo (Aunque yo prefiero dejárselo a la vista)
                }
                .onEach {
                    //Returns a flow which performs the given [action] on each value of the original flow.
                    //parar progressdialog
                }
                .catch { handleFailureFlow(it) }
                .collect {
                    when (it) {
                        is Success<List<Article>> -> {
                            handleArticlesList(it.data)
                        }
                        is Error -> {
                            handleFailureFlow(Throwable())
                        }
                    }
                }
        }
    }

    private fun handleArticlesList(articles: List<Article>) {
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
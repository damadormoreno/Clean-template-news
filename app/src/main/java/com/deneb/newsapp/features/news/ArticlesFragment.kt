package com.deneb.newsapp.features.news


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.deneb.newsapp.R
import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.extensions.failure
import com.deneb.newsapp.core.extensions.observe
import com.deneb.newsapp.core.extensions.viewModel
import com.deneb.newsapp.core.functional.DialogCallback
import com.deneb.newsapp.core.navigation.Navigator
import com.deneb.newsapp.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_articles.*
import javax.inject.Inject

class ArticlesFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_articles

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var articleAdapter: ArticleAdapter

    private lateinit var getArticlesViewModel: GetArticlesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        getArticlesViewModel = viewModel(viewModelFactory) {
            observe(articles, ::renderArticlesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        initListeners()
        loadArticles()
    }

    private fun initializeView() {
        articleList.layoutManager = LinearLayoutManager(activity)
        articleList.adapter = articleAdapter
    }

    private fun initListeners() {
        articleAdapter.clickListener = { articleView ->
            //TODO: llamar al navigator para acceder al detalle
        }
    }

    private fun loadArticles() {
        showProgress()
        getArticlesViewModel.getArticles()
    }

    private fun renderArticlesList(articles: List<ArticleView>?) {
        articleAdapter.collection = articles.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when(failure) {
            is Failure.CustomError -> renderFailure(failure.errorCode, failure.errorMessage)
            else -> renderFailure(0, "")
        }
    }

    private fun renderFailure(errorCode: Int, errorMessage: String?) {
        hideProgress()
        showError(errorCode, errorMessage, object : DialogCallback {
            override fun onAccept() {
                loadArticles()
            }

            override fun onDecline() {
                onBackPressed()
            }
        })
    }

}

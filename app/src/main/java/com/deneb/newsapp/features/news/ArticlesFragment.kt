package com.deneb.newsapp.features.news


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import android.view.View
import androidx.navigation.findNavController
import com.deneb.newsapp.R
import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.extensions.failure
import com.deneb.newsapp.core.extensions.observe
import com.deneb.newsapp.core.functional.DialogCallback
import com.deneb.newsapp.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope

class ArticlesFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_articles

    private val articleAdapter: ArticleAdapter by inject()
    private val getArticlesViewModel: GetArticlesViewModel by inject()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(getArticlesViewModel) {
            observe(articles, ::renderArticlesList)
            observe(loading, ::showLoading)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        initListeners()
        uiScope.launch { loadArticles() }
    }

    private fun initializeView() {

        articleList.layoutManager = LinearLayoutManager(activity)
        articleList.adapter = articleAdapter

        searchBarProfiles.onActionViewExpanded()
        searchBarProfiles.isFocusable = false
        searchBarProfiles.clearFocus()
        searchBarProfiles.queryHint = "Buscar"

    }

    private fun initListeners() {
        articleAdapter.clickListener = { articleView ->
            val bundle = Bundle()
            bundle.putSerializable("article", articleView)
            view?.findNavController()
                ?.navigate(R.id.action_articlesFragment_to_articleDetailFragment, bundle)
        }

        searchBarProfiles.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                getArticlesViewModel.filter(s)
                return false
            }
        })
    }

    private suspend fun loadArticles() {
        getArticlesViewModel.getArticlesFlow()
    }

    private fun renderArticlesList(articles: List<ArticleView>?) {
        articleAdapter.collection = articles.orEmpty()
        hideProgress()
    }

    private fun showLoading(show: Boolean?) {
        when (show!!) {
            true -> showProgress()
            false -> hideProgress()
        }


    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.CustomError -> renderFailure(failure.errorCode, failure.errorMessage)
            else -> renderFailure(0, "")
        }
    }

    private fun renderFailure(errorCode: Int, errorMessage: String?) {
        showError(errorCode, errorMessage, object : DialogCallback {
            override suspend fun onAccept() {
                loadArticles()
            }

            override suspend fun onDecline() {
                onBackPressed()
            }
        })
    }

}
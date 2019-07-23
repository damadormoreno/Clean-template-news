package com.deneb.newsapp.features.news


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import android.view.View
import android.widget.EditText
import com.deneb.newsapp.R
import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.extensions.failure
import com.deneb.newsapp.core.extensions.observe
import com.deneb.newsapp.core.extensions.viewModel
import com.deneb.newsapp.core.functional.DialogCallback
import com.deneb.newsapp.core.navigation.Navigator
import com.deneb.newsapp.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_articles.*
import org.koin.android.ext.android.inject
import javax.inject.Inject

class ArticlesFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_articles

    private val navigator: Navigator by inject()
    private val articleAdapter: ArticleAdapter by inject()
    private val getArticlesViewModel: GetArticlesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(getArticlesViewModel) {
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

        searchBarProfiles.onActionViewExpanded()
        searchBarProfiles.isFocusable = false
        searchBarProfiles.clearFocus()
        searchBarProfiles.queryHint = "Buscar"

    }

    private fun initListeners() {
        articleAdapter.clickListener = { articleView ->
            navigator.showArticleDetailFragment(activity!!, articleView)
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

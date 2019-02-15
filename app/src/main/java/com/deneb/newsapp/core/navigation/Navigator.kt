package com.deneb.newsapp.core.navigation

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.deneb.newsapp.features.news.ArticleDetailFragment
import com.deneb.newsapp.features.news.ArticleView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(){

    // Activities ==================================================================================
    private fun showMainActivity(context: Context) = context.startActivity(MainActivity.callingIntent(context))

    // Fragments ===================================================================================
    fun showArticleDetailFragment(activity: FragmentActivity, article: ArticleView) {
        (activity as MainActivity).replaceFragment(ArticleDetailFragment.newInstance(article), "ArticleDetailsFragment")
    }
}
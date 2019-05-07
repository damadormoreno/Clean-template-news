package com.deneb.newsapp.core.navigation

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.deneb.newsapp.features.news.ArticleDetailFragment
import com.deneb.newsapp.features.news.ArticleView
import javax.inject.Inject
import javax.inject.Singleton


class Navigator
{

    // Activities ==================================================================================
    private fun showMainActivity(context: Context) = context.startActivity(MainActivity.callingIntent(context))

    // Fragments ===================================================================================
    fun showArticleDetailFragment(activity: androidx.fragment.app.FragmentActivity, article: ArticleView) {
        (activity as MainActivity).replaceFragment(ArticleDetailFragment.newInstance(article), "ArticleDetailsFragment")
    }
}
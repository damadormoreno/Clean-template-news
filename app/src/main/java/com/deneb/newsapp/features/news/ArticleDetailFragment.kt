package com.deneb.newsapp.features.news


import android.os.Bundle
import android.view.View

import com.deneb.newsapp.R
import com.deneb.newsapp.core.extensions.loadFromUrl
import com.deneb.newsapp.core.navigation.Navigator
import com.deneb.newsapp.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_article_detail.*
import javax.inject.Inject


class ArticleDetailFragment : BaseFragment() {

    private var article: ArticleView? = null

    companion object {
        fun newInstance(article: ArticleView): ArticleDetailFragment {
            val fragment = ArticleDetailFragment()
            val args = Bundle()
            args.putSerializable("article", article)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var navigator: Navigator

    override fun layoutId(): Int = R.layout.fragment_article_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments
        if (arguments != null) {
            article = arguments.getSerializable("article") as ArticleView
            initLayout()
        }
    }

    private fun initLayout() {
        imageDetail.loadFromUrl(article?.urlToImage?:"")
        titleDetail.text = article?.title
        authorDetail.text = article?.author
        contentDetail.text = article?.content
    }

}

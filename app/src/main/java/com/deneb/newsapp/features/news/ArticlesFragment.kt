package com.deneb.newsapp.features.news


import android.os.Bundle
import com.deneb.newsapp.R
import com.deneb.newsapp.core.extensions.viewModel
import com.deneb.newsapp.core.navigation.Navigator
import com.deneb.newsapp.core.platform.BaseFragment
import javax.inject.Inject

class ArticlesFragment : BaseFragment() {
    override fun layoutId() = R.layout.fragment_articles

    @Inject
    lateinit var navigator: Navigator

    private lateinit var getArticlesViewModel: GetArticlesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        getArticlesViewModel = viewModel(viewModelFactory) {

        }
    }

}

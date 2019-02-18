package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.interactor.UseCase
import javax.inject.Inject

class GetArticles
(private val articlesRepository: ArticlesRepository): UseCase<List<Article>, GetArticles.Params>() {
    override suspend fun run(params: Params) = articlesRepository.getArticles()
    class Params

}
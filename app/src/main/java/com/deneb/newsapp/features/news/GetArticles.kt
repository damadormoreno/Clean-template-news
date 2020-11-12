package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.functional.Either
import com.deneb.newsapp.core.interactor.UseCaseFlow
import kotlinx.coroutines.flow.Flow

class GetArticles(private val articlesRepository: ArticlesRepository):
    UseCaseFlow<Either<Failure, List<Article>>,
            UseCaseFlow.None>() {
    override fun run(params: None?): Flow<Either<Failure, List<Article>>> = articlesRepository.getArticles()
}
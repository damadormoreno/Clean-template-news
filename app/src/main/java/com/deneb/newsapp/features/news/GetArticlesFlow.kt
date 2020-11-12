package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.di.DefaultDispatcher
import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.functional.Either
import com.deneb.newsapp.core.functional.Result
import com.deneb.newsapp.core.interactor.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class GetArticlesFlow(
    private val articlesRepository: ArticlesRepository,
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
):
    FlowUseCase<Any, List<Article>>(defaultDispatcher) {
    override fun execute(parameters: Any): Flow<Either<Failure, List<Article>>> {
        return articlesRepository.getArticles()
        }
    }
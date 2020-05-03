package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.functional.Result
import com.deneb.newsapp.core.interactor.UseCaseFlow
import kotlinx.coroutines.flow.Flow

class GetArticlesFlow(private val articlesRepository: ArticlesRepository) : UseCaseFlow<Result<List<Article>>, UseCaseFlow.None>(){
    override fun run(params: None?): Flow<Result<List<Article>>> = articlesRepository.getArticlesFlow()

}
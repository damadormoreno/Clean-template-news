package com.deneb.newsapp.features.news


import com.deneb.newsapp.core.functional.State
import com.deneb.newsapp.core.interactor.UseCaseFlow
import kotlinx.coroutines.flow.Flow

class GetArticlesFlow(private val articlesRepository: ArticlesRepository) : UseCaseFlow<State<List<Article>>, UseCaseFlow.None>(){
    override fun run(params: None?): Flow<State<List<Article>>> = articlesRepository.getArticlesFlow()

}
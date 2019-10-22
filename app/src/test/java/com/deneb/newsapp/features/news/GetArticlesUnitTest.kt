package com.deneb.newsapp.features.news

import com.deneb.newsapp.UnitTest
import com.deneb.newsapp.core.functional.Either
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetArticlesUnitTest : UnitTest() {

    @Mock private lateinit var mockArticlesRepository: ArticlesRepository
    private lateinit var getArticles: GetArticles

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        getArticles = GetArticles(mockArticlesRepository)
        given{ mockArticlesRepository.getArticles()}.willReturn(Either.Right(listOf(Article.empty())))
    }

    @Test
    fun `should get data from repository`() {

        runBlocking { getArticles.run(GetArticles.Params())}

        verify(mockArticlesRepository).getArticles()
        verifyNoMoreInteractions(mockArticlesRepository)
    }
}

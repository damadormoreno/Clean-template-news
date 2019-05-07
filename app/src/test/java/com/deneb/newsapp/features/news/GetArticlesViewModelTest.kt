package com.deneb.newsapp.features.news

import com.deneb.newsapp.AndroidTest
import com.deneb.newsapp.core.functional.Either
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.amshove.kluent.shouldEqualTo

class GetArticlesViewModelTest: AndroidTest() {
    private lateinit var getArticlesViewModel: GetArticlesViewModel

    @Mock private lateinit var getArticles: GetArticles

    @Before
    fun setUp() {
        getArticlesViewModel = GetArticlesViewModel(getArticles)
    }

    @Test
    fun `loading articles should update live data`(){
        val articlesList = listOf(
            Article(
                0,
                "David",
                "contenido",
                "descripcion",
                "27/03/2019",
                "titulo1",
                "https://www.google.es",
                "https://www.google.es"),
            Article(
                0,
                "Paco",
                "contenido",
                "descripcion",
                "27/03/2019",
                "titulo2",
                "https://www.google.es",
                "https://www.google.es")
        )

        given { runBlocking { getArticles.run(GetArticles.Params()) }}.willReturn(Either.Right(articlesList))

        getArticlesViewModel.articles.observeForever {
            it!!.size shouldEqualTo 2
            it[0].id shouldEqualTo 0
            it[1].id shouldEqualTo 1
        }
        runBlocking { getArticlesViewModel.getArticles() }
    }
}

package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.functional.Either
import com.deneb.newsapp.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface ArticlesRepository {

    fun getArticles(): Either<Failure, List<Article>>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: ArticlesService): ArticlesRepository {
        override fun getArticles(): Either<Failure, List<Article>> {
            return when(networkHandler.isConnected) {
                true -> request(service.getArticles(),
                    {
                        val articlesList: List<ArticleEntity> = it.articleEntities
                        articlesList.map {articleEntity ->
                            articleEntity.toArticle()
                        }
                    },
                    NewsEntity(emptyList(), "", 0) )
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError())
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError())
            }
        }
    }

}
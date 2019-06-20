package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.functional.Either
import com.deneb.newsapp.core.platform.NetworkHandler
import com.deneb.newsapp.core.platform.ServiceKOs
import retrofit2.Call
import java.util.*

interface ArticlesRepository {

    fun getArticles(): Either<Failure, List<Article>>
    fun getRemoteArticles(): Either<Failure, List<Article>>
    fun add(article: ArticleEntity): Either<Failure, Any>

    class Network
    (private val networkHandler: NetworkHandler,
                        private val service: ArticlesService,
                        private val local: ArticlesLocal,
                        private val fetch: FetchLocal): ArticlesRepository {
        
        override fun getRemoteArticles(): Either<Failure, List<Article>> {
            return when(networkHandler.isConnected) {
                true -> request(
                        service.getArticles(),
                        {
                            val articlesList: List<ArticleEntity> = it.articleEntities
                            fetch.addFetchDate(FetchEntity(0, Date().time))
                            addAllArticles(articlesList)
                            articlesList.map { articleEntity ->
                                articleEntity.toArticle()
                            }

                        },
                        NewsEntity(emptyList(), "", 0)
                    )
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        override fun getArticles(): Either<Failure, List<Article>> {
            return try {
                val articles = local.getArticles()
                val fetchDate: FetchEntity? = fetch.getFetchDate(0)
                if (articles.isNullOrEmpty() || fetchDate == null || isFetchCurrentNeeded(fetchDate.fetchData)){
                    getRemoteArticles()
                }else {
                    Either.Right(local.getArticles().map {
                        it.toArticle()
                    })
                }
            }catch (e: Exception) {
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }

        override fun add(article: ArticleEntity): Either<Failure, Any> {
            return try {
                Either.Right(local.addArticle(article))
            } catch (e: Exception) {
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }

        private fun addAllArticles(articles: List<ArticleEntity>) {
            for (article in articles) {
                local.addArticle(article)
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

        private fun isFetchCurrentNeeded(lastFetchTime: Long): Boolean {
            val oneMinuteInMillis = 60000
            val thirtyMinutesAgo = Date(lastFetchTime - (30 * oneMinuteInMillis)).time
            return Date(lastFetchTime).before(Date(thirtyMinutesAgo))
        }
    }
}
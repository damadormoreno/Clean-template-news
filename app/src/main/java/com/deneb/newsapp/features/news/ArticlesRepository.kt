package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.extensions.SharedPrefences
import com.deneb.newsapp.core.functional.Either
import com.deneb.newsapp.core.platform.NetworkHandler
import com.deneb.newsapp.core.platform.ServiceKOs
import org.threeten.bp.ZonedDateTime
import retrofit2.Call
import java.lang.Exception
import javax.inject.Inject

interface ArticlesRepository {

    fun getArticles(): Either<Failure, List<Article>>
    fun getRemoteArticles(): Either<Failure, List<Article>>
    fun add(article: ArticleEntity): Either<Failure, Any>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: ArticlesService,
                        private val local: ArticlesLocal): ArticlesRepository {
        
        override fun getRemoteArticles(): Either<Failure, List<Article>> {
            return when(networkHandler.isConnected) {
                true -> request(
                        service.getArticles(),
                        {
                            val articlesList: List<ArticleEntity> = it.articleEntities
                            addAllArticles(articlesList)
                            SharedPrefences.defaultPrefs()
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
                if (articles.isNullOrEmpty() or isFetchCurrentNeeded()){
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

        private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
            val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
            return lastFetchTime.isBefore(thirtyMinutesAgo)
        }
    }


}
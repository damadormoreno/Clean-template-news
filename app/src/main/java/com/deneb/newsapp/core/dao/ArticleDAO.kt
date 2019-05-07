package com.deneb.newsapp.core.dao

import androidx.room.*
import com.deneb.newsapp.features.news.ArticleEntity

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(articleEntity: ArticleEntity)

    @Update
    fun updateEvent(articleEntity: ArticleEntity)

    @Delete
    fun deleteEvent(articleEntity: ArticleEntity)

    @Query("SELECT * FROM ArticleEntity")
    fun getArticles(): List<ArticleEntity>

    @Query("SELECT * FROM ArticleEntity WHERE id == :articleId")
    fun getArticleById(articleId: Int): List<ArticleEntity>
}
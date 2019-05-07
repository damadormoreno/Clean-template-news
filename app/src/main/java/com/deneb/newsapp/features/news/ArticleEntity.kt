package com.deneb.newsapp.features.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String?,

    @SerializedName("content")
    @ColumnInfo(name = "content")
    val content: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @SerializedName("publishedAt")
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String?,

    @SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?
) {
    companion object {
        fun empty() = ArticleEntity(0,
            "",
            /*SourceEntity("",""),*/
            "",
            "",
            "",
            "",
            "",
            "")
    }

    fun toArticle(): Article {
        return Article(
            id,
            author?:"",
            content?:"",
            description?:"",
            publishedAt?:"",
            title?:"",
            url?:"",
            urlToImage?:"")
    }
}
package com.deneb.newsapp.features.news

import java.io.Serializable

data class ArticleView(
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable {
    companion object {
        fun empty() : ArticleView = ArticleView(
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            "")
    }

}
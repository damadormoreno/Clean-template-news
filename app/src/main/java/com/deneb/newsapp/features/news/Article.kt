package com.deneb.newsapp.features.news

data class Article(
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
) {
    fun toArticleEntity(): ArticleEntity {
        return ArticleEntity(
            id,
            author,
            content,
            description,
            publishedAt,
            title,
            url,
            urlToImage
            )
    }

    fun toArticleView(): ArticleView{
        return ArticleView(
            id,
            author,
            content,
            description,
            publishedAt,
            title,
            url,
            urlToImage
        )
    }
    companion object {
        fun empty() = Article(
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
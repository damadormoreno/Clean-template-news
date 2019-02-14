package com.deneb.newsapp.features.news

import com.google.gson.annotations.SerializedName

data class NewsEntity(
    @SerializedName("articles")
    val articleEntities: List<ArticleEntity>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)
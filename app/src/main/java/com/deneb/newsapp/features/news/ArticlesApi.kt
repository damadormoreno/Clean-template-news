package com.deneb.newsapp.features.news


import retrofit2.Call
import retrofit2.http.GET

internal interface ArticlesApi {

    @GET("top-headlines?country=us&apiKey=4861faaa88f54988b618a6c95cf91d2d")
    fun getArticles(): Call<NewsEntity>
}
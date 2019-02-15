package com.deneb.newsapp.features.news

interface FetchDbLocal {
    fun getFetchDate(id: Int): FetchEntity
    fun addFetchDate(fetchEntity: FetchEntity): Any
}
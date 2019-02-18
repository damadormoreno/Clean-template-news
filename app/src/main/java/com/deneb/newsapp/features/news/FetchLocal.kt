package com.deneb.newsapp.features.news

import com.deneb.newsapp.core.dataBase.AppDatabase
import com.deneb.newsapp.core.platform.ContextHandler
import javax.inject.Inject
import javax.inject.Singleton


class FetchLocal
(contextHandler: ContextHandler): FetchDbLocal {

    private val fetchDb by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.fetchEntityDao()!!
    }

    override fun getFetchDate(id: Int): FetchEntity = fetchDb.getFetchDataById(id)
    override fun addFetchDate(fetchEntity: FetchEntity) = fetchDb.insertFetchDate(fetchEntity)
}
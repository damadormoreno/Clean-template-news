package com.deneb.newsapp.core.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.deneb.newsapp.core.dao.ArticleDAO
import com.deneb.newsapp.core.dao.FetchDateDAO
import com.deneb.newsapp.features.news.ArticleEntity
import com.deneb.newsapp.features.news.FetchEntity

@Database(entities = [ArticleEntity::class, FetchEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun articleEntityDao(): ArticleDAO
    abstract fun fetchEntityDao(): FetchDateDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "newsDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
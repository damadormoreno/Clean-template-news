package com.deneb.newsapp.core.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.deneb.newsapp.core.dao.ArticleDAO
import com.deneb.newsapp.features.news.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun articleEntityDao(): ArticleDAO

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
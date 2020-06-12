package com.deneb.newsapp.core.di

import androidx.room.Room
import com.deneb.newsapp.core.dataBase.AppDatabase
import com.deneb.newsapp.features.news.ArticlesLocal
import com.deneb.newsapp.features.news.FetchLocal
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "newsDB"
        )
            .build()
    }
    factory { FetchLocal(get(), get()) }
    factory { ArticlesLocal(get(), get()) }
}
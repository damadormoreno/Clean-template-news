package com.deneb.newsapp

import android.app.Application
import com.deneb.newsapp.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(listOf(
                networkModule,
                applicationModule,
                databaseModule,
                datasourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            ))
        }

    }
}
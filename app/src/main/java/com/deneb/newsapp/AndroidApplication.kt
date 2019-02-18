package com.deneb.newsapp

import android.app.Application
import com.deneb.newsapp.core.di.*
import org.koin.android.ext.android.startKoin

class AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))

    }
}
package com.deneb.newsapp

import android.app.Application
import com.deneb.newsapp.core.di.ApplicationComponent
import com.deneb.newsapp.core.di.ApplicationModule
import com.deneb.newsapp.core.di.DaggerApplicationComponent
import com.jakewharton.threetenabp.AndroidThreeTen

class AndroidApplication: Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        AndroidThreeTen.init(this)
    }

    private fun injectMembers() = appComponent.inject(this)
}
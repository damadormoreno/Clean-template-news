package com.deneb.newsapp

import android.app.Application
import com.deneb.newsapp.core.di.ApplicationComponent
import com.deneb.newsapp.core.di.ApplicationModule
import com.deneb.newsapp.core.di.DaggerApplicationComponent

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
    }

    private fun injectMembers() = appComponent.inject(this)
}
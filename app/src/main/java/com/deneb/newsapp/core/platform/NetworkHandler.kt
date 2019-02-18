package com.deneb.newsapp.core.platform

import android.content.Context
import com.deneb.newsapp.core.extensions.networkInfo
import javax.inject.Inject
import javax.inject.Singleton


class NetworkHandler
(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}
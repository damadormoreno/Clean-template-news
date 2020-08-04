package com.deneb.newsapp.core.platform

import android.content.Context
import com.deneb.newsapp.core.extensions.checkNetworkState


class NetworkHandler
(private val context: Context) {
    val isConnected get() = context.checkNetworkState()
}
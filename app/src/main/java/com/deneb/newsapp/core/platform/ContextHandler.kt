package com.deneb.newsapp.core.platform

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which handles context for access room database.
 */

class ContextHandler
(private val context: Context) {
    val appContext: Context get() = context.applicationContext
}
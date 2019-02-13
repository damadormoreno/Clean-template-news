package com.deneb.newsapp.core.platform

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which handles context for access room database.
 */
@Singleton
class ContextHandler
@Inject constructor(private val context: Context) {
    val appContext: Context get() = context.applicationContext
}
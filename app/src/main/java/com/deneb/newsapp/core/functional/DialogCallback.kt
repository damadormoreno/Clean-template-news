package com.deneb.newsapp.core.functional

interface DialogCallback {
    suspend fun onAccept()
    suspend fun onDecline()
}
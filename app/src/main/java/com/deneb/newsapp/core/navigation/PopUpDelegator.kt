package com.deneb.newsapp.core.navigation

import com.deneb.newsapp.core.functional.DialogCallback

interface PopUpDelegator {

    fun showErrorWithRetry(title: String, message: String, positiveText: String,
                           negativeText: String, callback: DialogCallback
    )
}
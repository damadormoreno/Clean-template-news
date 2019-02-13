package com.deneb.newsapp.core.platform

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deneb.newsapp.AndroidApplication
import com.deneb.newsapp.R
import com.deneb.newsapp.core.di.ApplicationComponent
import com.deneb.newsapp.core.functional.DialogCallback
import com.deneb.newsapp.core.navigation.PopUpDelegator
import kotlinx.android.synthetic.main.activity_layout.*
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    private var popUpDelegator: PopUpDelegator? = null

    abstract fun layoutId(): Int


    val appComponent: ApplicationComponent by lazy(LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    open fun onBackPressed() {

    }

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) {
            if (this is BaseActivity) this.progress.visibility = viewStatus
        }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is PopUpDelegator) {
            this.popUpDelegator = activity
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PopUpDelegator) {
            this.popUpDelegator = context
        }
    }

    internal fun showErrorWithRetry(title: String, message: String, positiveText: String,
                                    negativeText: String, callback: DialogCallback) {
        popUpDelegator?.showErrorWithRetry(title, message, positiveText, negativeText, callback)
    }

    internal fun showError(errorCode: Int, errorMessage: String?, dialogCallback: DialogCallback) {
        val genericErrorTitle = getString(R.string.generic_error_title)
        val genericErrorMessage = getString(R.string.generic_error_body)
        showErrorWithRetry(
            genericErrorTitle,
            genericErrorMessage,
            getString(R.string.Retry),
            getString(R.string.Cancel),
            object : DialogCallback {
                override fun onDecline() = dialogCallback.onDecline()
                override fun onAccept() = dialogCallback.onAccept()
            })
    }
}
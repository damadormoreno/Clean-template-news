package com.deneb.newsapp.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deneb.newsapp.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var failureFlow: MutableLiveData<Throwable> = MutableLiveData()
    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    protected fun handleFailureFlow(failure: Throwable) {
        this.failureFlow.value = failure
    }


}
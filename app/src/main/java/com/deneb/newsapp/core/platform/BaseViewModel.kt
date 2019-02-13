package com.deneb.newsapp.core.platform

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.deneb.newsapp.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
package com.deneb.newsapp.core.interactor

import kotlinx.coroutines.flow.Flow

abstract class UseCaseFlow <out Type, in Params> where Type : Any? {
    abstract fun run(params: Params? = null): Flow<Type>
    @JvmOverloads
    operator fun invoke(
        params: Params? = null
    ):Flow<Type>{
        return run(params)
    }

    class None
}
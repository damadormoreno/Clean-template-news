package com.deneb.newsapp.core.interactor

import com.deneb.newsapp.core.exception.Failure
import com.deneb.newsapp.core.functional.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import com.deneb.newsapp.core.functional.Result

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    @ExperimentalCoroutinesApi
    operator fun invoke(parameters: P): Flow<Either<Failure, R>> {
        return execute(parameters)
            .catch { e -> emit(Either.Left(Failure.CustomError(10101, e.message?:""))) }
            .flowOn(coroutineDispatcher)
    }

    abstract fun execute(parameters: P): Flow<Either<Failure, R>>
}
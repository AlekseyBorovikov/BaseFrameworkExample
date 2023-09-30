package com.example.common.framework.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Base viewmodel class for all application viewmodels.
 *
 * @param T [ViewState] type
 */
abstract class MvvmViewModel<T: ViewState>: ViewModel() {

    private val _uiState by lazy { MutableStateFlow(initState()) }
    val uiState = _uiState.asStateFlow()

    /**
     * A method that guarantees state initialization
     * */
    abstract fun initState(): T

    /**
     * A method that allows you to simply update the state
     * */
    fun updateState(function: T.() -> T) {
        _uiState.update { state -> function(state) }
    }

    /**
     * Error handler for viewModelScope
     * */
    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(SAFE_LAUNCH_EXCEPTION).e(exception)
        handleError(exception)
    }

    /**
     * All errors that may occur in the internal methods of this class should be handled here
     * */
    open fun handleError(exception: Throwable) {}

    /**
     * Method that will be called when data loading starts using the execute method
     * */
    open fun startLoading() = Unit

    /**
     * Method that will be called when the data is loaded using the execute method
     * */
    open fun finishLoading() = Unit

    /**
     * Does the work in viewModelScope with custom error handler
     * */
    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    /**
     * Executing a usecase query that returns data without [Result]
     *
     * @see [com.example.app.libraries.framework.usecase.DataStateUseCase]
     * */
    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { handleError(it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    /**
     * Executing a usecase query that returns data with [Result]
     *
     * @see [com.example.app.libraries.framework.usecase.DataStateUseCase]
     * */
    protected suspend fun <T> execute(
        callFlow: Flow<Result<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .onStart { startLoading() }
            .catch { handleError(it) }
            .collect { state ->
                finishLoading()
                state
                    .onFailure { handleError(it) }
                    .onSuccess { completionHandler.invoke(it) }
            }
    }

    /**
     * Executing a usecase query that returns data with [Result],
     * but does not execute [startLoading] and [finishLoading] methods
     *
     * @see [com.example.app.libraries.framework.usecase.DataStateUseCase]
     * */
    protected suspend fun <T> executeInBackground(
        callFlow: Flow<Result<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { handleError(it) }
            .collect { state ->
                state
                    .onFailure { handleError(it) }
                    .onSuccess { completionHandler.invoke(it) }
            }
    }

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-ExceptionHandler"
    }
}
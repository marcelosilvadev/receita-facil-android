package br.com.marcelossilva.receitafacil.core.util.extensions

import br.com.marcelossilva.receitafacil.core.util.ResponseData
import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<ResponseData<T>>.observeState(
    onLoading: suspend () -> Unit = {},
    onEmpty: suspend () -> Unit = {},
    onSuccess: suspend (data: T) -> Unit,
    onFailure: suspend (error: Throwable) -> Unit
) {
    collect {response ->
        when (response) {
            ResponseData.Loading -> onLoading.invoke()
            ResponseData.Empty -> onEmpty.invoke()
            is ResponseData.Success -> onSuccess.invoke(response.data)
            is ResponseData.Error -> onFailure.invoke(response.throwable)
        }
    }
}
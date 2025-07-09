package br.com.marcelossilva.receitafacil.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T: Any> SingleEventEffect(
    sideEffectFlow: Flow<T>,
    livecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(sideEffectFlow) {
        lifecycleOwner.repeatOnLifecycle(livecycleState){
            sideEffectFlow.collect(collector)
        }

    }
}
package br.com.robsonribeiroft.androidnews.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T, in Params>(private val coroutineCtx: CoroutineCtx = CoroutineCtx()) {

    abstract suspend fun run(params: Params? = null): T

    operator fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        onError: (Throwable) -> Unit,
        onComplete: (T)-> Unit
    ) {
        scope.launch(coroutineCtx.io) {
            try {
                run(params).also {
                    withContext(coroutineCtx.main) {
                        onComplete(it)
                    }
                }
            } catch (e: Exception) {
                withContext(coroutineCtx.main){
                    onError(e)
                }
            }
        }
    }
}

data class CoroutineCtx(
    val io: CoroutineContext = Dispatchers.IO,
    val main: CoroutineContext = Dispatchers.Main
)
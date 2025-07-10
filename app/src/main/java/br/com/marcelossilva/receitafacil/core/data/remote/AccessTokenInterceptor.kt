package br.com.marcelossilva.receitafacil.core.data.remote

import br.com.marcelossilva.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.logging.logError
import br.com.marcelossilva.receitafacil.core.util.logging.logInfo
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val localDataSource: DataStoreLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : Interceptor {

    companion object {
        const val TOKEN_TYPE = "Bearer"
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val data = try {
            //Runblocking vai executar a chamada de forma síncrona
            runBlocking(dispatcherProvider.io()) {
                localDataSource.getData().firstOrNull()
            }
        } catch (e: Exception) {
            logError("INTERCEPTOR", "Erro ao buscar token")
            null
        }
        val request = chain.request().newBuilder()

        data?.let { userData ->
            request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${userData.token}")
        } ?: run {
            logInfo("INTERCEPTOR", "Token não encontrado")
        }

        return chain.proceed(request.build())
    }
}
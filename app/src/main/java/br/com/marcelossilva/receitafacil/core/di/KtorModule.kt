package br.com.marcelossilva.receitafacil.core.di

import br.com.marcelossilva.receitafacil.BuildConfig
import br.com.marcelossilva.receitafacil.core.data.remote.AccessTokenInterceptor
import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApiImpl
import com.google.gson.Gson
import com.google.gson.Strictness
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.gson.gson
import okhttp3.OkHttpClient
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        okHttpClient: OkHttpClient,
        accessTokenInterceptor: AccessTokenInterceptor
    ): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                preconfigured = okHttpClient
                config {
                    addInterceptor(accessTokenInterceptor)
                }
            }
            install(ContentNegotiation) {
                gson {
                    setStrictness(Strictness.LENIENT)
                    setPrettyPrinting()
                    serializeNulls()
                }
            }
            install(WebSockets)
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("Ktor").d(message)
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                url(BuildConfig.BASE_URL)
            }
        }
    }

    @Provides
    @Singleton
    fun provideRecipesServiceApi(
        httpClient: HttpClient
    ): RecipesServiceApi {
        return RecipesServiceApiImpl(httpClient)
    }
}
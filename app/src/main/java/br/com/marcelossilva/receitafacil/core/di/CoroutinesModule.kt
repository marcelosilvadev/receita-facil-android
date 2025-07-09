package br.com.marcelossilva.receitafacil.core.di

import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun provideDispatcherProvider(
        dispatcherProvider: DispatcherProviderImpl
    ): DispatcherProvider
}
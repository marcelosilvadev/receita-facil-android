package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.di

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.repository.LoginRepositoryImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.source.LoginRemoteDataSourceImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginDataSource(
        recipesServiceApi: RecipesServiceApi
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(recipesServiceApi)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        dispatcherProvider: DispatcherProvider,
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCaseImpl(dispatcherProvider, loginRepository)
    }

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase{
        return ValidateLoginInputUseCaseImpl()
    }
}
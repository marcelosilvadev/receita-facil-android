package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.di

import br.com.marcelossilva.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.repository.LoginRepositoryImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.source.LoginRemoteDataSourceImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCaseImpl
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
        loginRemoteDataSource: LoginRemoteDataSource,
        localDataSource: DataStoreLocalDataSource,
        dispatcherProvider: DispatcherProvider
    ): LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource, localDataSource, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideRemoveUserDataUseCase(
        loginRepository: LoginRepository
    ): RemoveUserDataUseCase {
        return RemoveUserDataUseCaseImpl(loginRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserDataUseCase(
        loginRepository: LoginRepository
    ): SaveUserDataUseCase {
        return SaveUserDataUseCaseImpl(loginRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserDataUseCase(
        loginRepository: LoginRepository
    ): GetUserDataUseCase {
        return GetUserDataUseCaseImpl(loginRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCaseImpl(loginRepository)
    }

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }
}
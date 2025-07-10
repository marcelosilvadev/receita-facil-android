package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.di

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.repository.RegisterUserRepositoryImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.source.RegisterUserRemoteDataSourceImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.repository.RegisterUserRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.source.RegisterUserRemoteDataSource
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.usecase.RegisterUserUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.usecase.RegisterUserUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.usecase.ValidateRegisterInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterUserModule {

    @Provides
    @Singleton
    fun provideRegisterUserDataSource(
        recipesServiceApi: RecipesServiceApi
    ): RegisterUserRemoteDataSource {
        return RegisterUserRemoteDataSourceImpl(recipesServiceApi = recipesServiceApi)
    }

    @Provides
    @Singleton
    fun provideRegisterUserRepository(
        registerUserRemoteDataSource: RegisterUserRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): RegisterUserRepository {
        return RegisterUserRepositoryImpl(
            remoteDataSource = registerUserRemoteDataSource,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(
        registerUserRepository: RegisterUserRepository,
    ): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(
            registerUserRepository = registerUserRepository
        )
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase(
    ): ValidateRegisterInputUseCaseImpl {
        return ValidateRegisterInputUseCaseImpl()
    }
}
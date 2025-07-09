package br.com.marcelossilva.receitafacil.ui.presentation.features.register.di

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.data.repository.RegisterUserRepositoryImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.data.source.RegisterUserRemoteDataSourceImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.repository.RegisterUserRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCaseImpl
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
        registerUserRemoteDataSource: RegisterUserRemoteDataSource
    ): RegisterUserRepository {
        return RegisterUserRepositoryImpl(remoteDataSource = registerUserRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(
        registerUserRepository: RegisterUserRepository,
        dispatcherProvider: DispatcherProvider
    ): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(
            registerUserRepository = registerUserRepository,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase(
        registerUserRepository: RegisterUserRepository,
        dispatcherProvider: DispatcherProvider
    ): ValidateRegisterInputUseCaseImpl {
        return ValidateRegisterInputUseCaseImpl()
    }
}
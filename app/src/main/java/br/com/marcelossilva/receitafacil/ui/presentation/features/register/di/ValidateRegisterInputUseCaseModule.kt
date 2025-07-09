package br.com.marcelossilva.receitafacil.ui.presentation.features.register.di

import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ValidateRegisterInputUseCaseModule {
    @Binds
    @Singleton
    abstract fun bindValidateRegisterInputUseCase(
        impl: ValidateRegisterInputUseCaseImpl
    ): ValidateRegisterInputUseCase
}


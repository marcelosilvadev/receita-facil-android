package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.di

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.data.repository.AddUpdateRecipeRepositoryImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.data.source.AddUpdateRecipeRemoteDataSourceImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.source.AddUpdateRecipeRemoteDataSource
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.AddRecipeUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.AddRecipeUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.UpdateRecipeUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.UpdateRecipeUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateAddUpdateRecipeInputUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateAddUpdateRecipeInputUseCaseImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateDialogInputUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateDialogInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddUpdateRecipeModule {

    @Provides
    @Singleton
    fun provideAddUpdateRecipeRemoteDataSource(
        recipesServiceApi: RecipesServiceApi
    ): AddUpdateRecipeRemoteDataSource {
        return AddUpdateRecipeRemoteDataSourceImpl(recipesServiceApi)
    }

    @Provides
    @Singleton
    fun provideAddUpdateRecipeRepository(
        remoteDataSource: AddUpdateRecipeRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): AddUpdateRecipeRepository {
        return AddUpdateRecipeRepositoryImpl(remoteDataSource, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideAddRecipeUseCase(
        repository: AddUpdateRecipeRepository
    ): AddRecipeUseCase {
        return AddRecipeUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateRecipeUseCase(
        repository: AddUpdateRecipeRepository
    ): UpdateRecipeUseCase {
        return UpdateRecipeUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideValidateAddUpdateRecipeInputUseCase(
    ): ValidateAddUpdateRecipeInputUseCase {
        return ValidateAddUpdateRecipeInputUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideValidateDialogInputUseCase(
    ): ValidateDialogInputUseCase {
        return ValidateDialogInputUseCaseImpl()
    }
}
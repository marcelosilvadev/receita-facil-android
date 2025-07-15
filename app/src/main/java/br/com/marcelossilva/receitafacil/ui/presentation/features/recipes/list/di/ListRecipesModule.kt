package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.di

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.data.repository.GetRecipesByUserRepositoryImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.data.source.GetRecipesByUserRemoteDataSourceImpl
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.usecase.GetRecipesByUserUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.usecase.GetRecipesByUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListRecipesModule {

    @Provides
    @Singleton
    fun provideGetRecipesByUserRemoteDataSource(
        recipesServiceApi: RecipesServiceApi
    ): GetRecipesByUserRemoteDataSource {
        return GetRecipesByUserRemoteDataSourceImpl(recipesServiceApi)
    }

    @Provides
    @Singleton
    fun provideGetRecipesByUserRepository(
        remoteDataSource: GetRecipesByUserRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): GetRecipesByUserRepository {
        return GetRecipesByUserRepositoryImpl(remoteDataSource, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideGetRecipesByUserUseCase(
        getRecipesByUserRepository: GetRecipesByUserRepository
    ): GetRecipesByUserUseCase {
        return GetRecipesByUserUseCaseImpl(getRecipesByUserRepository)
    }
}
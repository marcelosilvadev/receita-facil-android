package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.data.repository

import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecipesByUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: GetRecipesByUserRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : GetRecipesByUserRepository {
    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponseModel>> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.getRecipesByUser(category)
        }
    }
}
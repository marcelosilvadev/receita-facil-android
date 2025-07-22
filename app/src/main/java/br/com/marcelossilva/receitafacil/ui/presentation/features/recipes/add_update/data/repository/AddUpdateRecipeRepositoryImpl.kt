package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.data.repository

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.source.AddUpdateRecipeRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddUpdateRecipeRepositoryImpl @Inject constructor(
    private val addUpdateRecipeRemoteDataSource: AddUpdateRecipeRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : AddUpdateRecipeRepository {
    override suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel> {
        return withContext(dispatcherProvider.io()) {
            addUpdateRecipeRemoteDataSource.addRecipe(addUpdateRecipeRequestModel)
        }
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimpleResponseModel> {
        return withContext(dispatcherProvider.io()) {
            addUpdateRecipeRemoteDataSource.updateRecipe(recipeId, addUpdateRecipeRequestModel)
        }
    }
}
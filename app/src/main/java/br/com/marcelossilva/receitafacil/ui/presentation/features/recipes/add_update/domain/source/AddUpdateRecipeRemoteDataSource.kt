package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.source

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel

interface AddUpdateRecipeRemoteDataSource {
    suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel>
    suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimpleResponseModel>
}
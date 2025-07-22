package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.repository

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel

interface AddUpdateRecipeRepository {
    suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel>
    suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimpleResponseModel>
}
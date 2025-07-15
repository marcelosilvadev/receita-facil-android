package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.source

import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel
import br.com.marcelossilva.receitafacil.core.util.ServiceResult

interface GetRecipesByUserRemoteDataSource {
    suspend fun getRecipesByUser(category: Int? = null): ServiceResult<List<RecipesResponseModel>>
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.data.source

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.domain.exceptions.ErrorResponseException
import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.data.mapper.toRecipeResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class GetRecipesByUserRemoteDataSourceImpl @Inject constructor(
    private val recipesApiService: RecipesServiceApi,
) :
    GetRecipesByUserRemoteDataSource {
    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponseModel>> {
        return try {
            val recipesResponse = recipesApiService.getRecipesByUser(category)
            val recipesResponseModel = recipesResponse.map {
                it.toRecipeResponseModel()
            }
            ServiceResult.Success(recipesResponseModel)
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}
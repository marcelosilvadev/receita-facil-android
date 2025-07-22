package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.data.source

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.domain.exceptions.ErrorResponseException
import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimpleResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.data.mapper.toAddUpdateRecipeRequest
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.source.AddUpdateRecipeRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class AddUpdateRecipeRemoteDataSourceImpl @Inject constructor(
    private val serviceApi: RecipesServiceApi,
) : AddUpdateRecipeRemoteDataSource {
    override suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel> {
        return try {
            val request = addUpdateRecipeRequestModel.toAddUpdateRecipeRequest()
            val recipesResponse = serviceApi.addRecipe(request)

            if (recipesResponse.isSuccessful) {
                ServiceResult.Success(recipesResponse.toSimpleResponseModel())
            } else {
                ServiceResult.Error(message = recipesResponse.message)
            }

        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimpleResponseModel> {
        return try {
            val request = addUpdateRecipeRequestModel.toAddUpdateRecipeRequest()
            val response = serviceApi.updateRecipe(recipeId, request)
            if (response.isSuccessful) {
                ServiceResult.Success(response.toSimpleResponseModel())
            } else {
                ServiceResult.Error(message = response.message)
            }
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ResponseData
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.core.util.Task
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UpdateRecipeUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimpleResponseModel>>
    data class Parameters(
        val recipeId: String,
        val addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    )
}

class UpdateRecipeUseCaseImpl @Inject constructor(
    private val addUpdateRecipeRepository: AddUpdateRecipeRepository
) : UpdateRecipeUseCase, Task<UpdateRecipeUseCase.Parameters, SimpleResponseModel>() {
    override suspend fun executeTask(parameters: UpdateRecipeUseCase.Parameters): ResponseData<SimpleResponseModel> {
        return try {
            when (val response =
                addUpdateRecipeRepository.updateRecipe(
                    parameters.recipeId,
                    parameters.addUpdateRecipeRequestModel
                )) {
                is ServiceResult.Success -> {
                    ResponseData.Success(response.data)
                }

                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }

}
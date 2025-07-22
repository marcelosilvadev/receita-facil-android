package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ResponseData
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.core.util.Task
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AddRecipeUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimpleResponseModel>>
    data class Parameters(
        val addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    )
}

class AddRecipeUseCaseImpl @Inject constructor(
    private val addUpdateRecipeRepository: AddUpdateRecipeRepository
) : AddRecipeUseCase,
    Task<AddRecipeUseCase.Parameters, SimpleResponseModel>() {
    override suspend fun executeTask(parameters: AddRecipeUseCase.Parameters): ResponseData<SimpleResponseModel> {
        return try {
            when (val response =
                addUpdateRecipeRepository.addRecipe(parameters.addUpdateRecipeRequestModel)) {
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
package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.usecase

import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel
import br.com.marcelossilva.receitafacil.core.util.ResponseData
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.core.util.Task
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import kotlinx.coroutines.flow.Flow

interface GetRecipesByUserUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<List<RecipesResponseModel>>>
    data class Parameters(
        val category: Int? = null
    )
}

class GetRecipesByUserUseCaseImpl(
    private val getRecipesByUserRepository: GetRecipesByUserRepository
) : GetRecipesByUserUseCase,
    Task<GetRecipesByUserUseCase.Parameters, List<RecipesResponseModel>>() {
    override suspend fun executeTask(parameters: GetRecipesByUserUseCase.Parameters): ResponseData<List<RecipesResponseModel>> {
        return try {
            when (val response = getRecipesByUserRepository.getRecipesByUser(parameters.category)) {
                is ServiceResult.Success -> {
                    if (response.data.isEmpty()) {
                        ResponseData.Empty
                    } else {
                        ResponseData.Success(response.data)
                    }
                }

                is ServiceResult.Error -> ResponseData.Error(Throwable(response.message))
            }
        } catch (e: Throwable) {
            ResponseData.Error(e)
        }
    }

}
package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.usecase

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ResponseData
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.core.util.Task
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.repository.RegisterUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RegisterUserUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimpleResponseModel>>
    data class Parameters(val addUserRequestModel: AddUserRequestModel)
}

class RegisterUserUseCaseImpl @Inject constructor(
    private val registerUserRepository: RegisterUserRepository
) : RegisterUserUseCase, Task<RegisterUserUseCase.Parameters, SimpleResponseModel>() {

    override suspend fun executeTask(parameters: RegisterUserUseCase.Parameters): ResponseData<SimpleResponseModel> {
        return try {
            val response = registerUserRepository.registerUser(parameters.addUserRequestModel)
            when (response) {
                is ServiceResult.Success -> ResponseData.Success(response.data)
                is ServiceResult.Error -> ResponseData.Error(Throwable(response.message))
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }
}
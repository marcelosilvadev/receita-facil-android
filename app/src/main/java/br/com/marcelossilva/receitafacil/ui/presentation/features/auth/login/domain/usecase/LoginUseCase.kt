package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase

import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.ResponseData
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.core.util.Task
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginUseCase {
    operator fun invoke(parameters: Parameters):Flow<ResponseData<TokenResponseModel>>
    data class Parameters(
        val authUserRequestModel: AuthUserRequestModel
    )
}

class LoginUseCaseImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val loginRepository: LoginRepository
): LoginUseCase, Task<LoginUseCase.Parameters, TokenResponseModel>() {
    override suspend fun executeTask(parameters: LoginUseCase.Parameters): ResponseData<TokenResponseModel> {
        return try {
            withContext(dispatcherProvider.io()){
                val response = loginRepository.login(parameters.authUserRequestModel)
                when(response) {
                    is ServiceResult.Success -> ResponseData.Success(response.data)
                    is ServiceResult.Error -> ResponseData.Error(Throwable(response.message))
                }
            }
        }catch (e: Exception) {
            ResponseData.Error(e)
        }

    }


}
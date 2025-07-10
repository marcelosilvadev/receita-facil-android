package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase

import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.ResponseData
import br.com.marcelossilva.receitafacil.core.util.Task
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SaveUserDataUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<Unit>>
    data class Parameters(
        val token: String,
        val userName: String
    )
}

class SaveUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcherProvider: DispatcherProvider
) : SaveUserDataUseCase, Task<SaveUserDataUseCase.Parameters, Unit>() {
    override suspend fun executeTask(parameters: SaveUserDataUseCase.Parameters): ResponseData<Unit> {
        return try {
            withContext(dispatcherProvider.io()) {
                ResponseData.Success(
                    loginRepository.saveData(
                        token = parameters.token,
                        userName = parameters.userName
                    )
                )
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }

}
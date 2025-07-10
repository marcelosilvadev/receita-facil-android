package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase

import br.com.marcelossilva.receitafacil.core.domain.model.UserData
import br.com.marcelossilva.receitafacil.core.util.FlowTask
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.take
import javax.inject.Inject

interface GetUserDataUseCase {
    suspend fun invoke(parameters: Unit = Unit): Flow<UserData>
}

class GetUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : GetUserDataUseCase, FlowTask<Unit, UserData>() {
    override suspend fun executeTaskFlow(parameters: Unit): Flow<UserData> {
        return loginRepository.getData()
            .catch { error -> // Captura erros e emite um UserData com mensagem de erro
                emit(
                    UserData(
                        errorMessage = error.message ?: "Erro desconhecido",
                    )
                )
            }.take(1) // Limita a emissão a apenas um valor
    }
}
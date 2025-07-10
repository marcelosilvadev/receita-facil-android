package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.repository

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.repository.RegisterUserRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.source.RegisterUserRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : RegisterUserRepository {
    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.registerUser(addUserRequestModel)
        }
    }
}
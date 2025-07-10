package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.repository

import br.com.marcelossilva.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import br.com.marcelossilva.receitafacil.core.domain.model.UserData
import br.com.marcelossilva.receitafacil.core.util.DispatcherProvider
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val localDataSource: DataStoreLocalDataSource,
    private val dispatcherProvider: DispatcherProvider
) : LoginRepository {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return withContext(dispatcherProvider.io()) {
            loginRemoteDataSource.login(authUserRequestModel)
        }
    }

    override fun getData(): Flow<UserData> = localDataSource.getData()

    override suspend fun saveData(token: String, userName: String) {
        return withContext(dispatcherProvider.io()) {
            localDataSource.saveData(token, userName)
        }
    }


    override suspend fun clearAll() {
        return withContext(dispatcherProvider.io()) {
            localDataSource.clearAll()
        }
    }
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository

import br.com.marcelossilva.receitafacil.core.domain.model.UserData
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel>
    fun getData(): Flow<UserData>
    suspend fun saveData(token: String, userName: String)
    suspend fun clearAll()
}
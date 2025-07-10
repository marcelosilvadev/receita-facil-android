package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository

import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

interface LoginRepository {
    suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel>
}
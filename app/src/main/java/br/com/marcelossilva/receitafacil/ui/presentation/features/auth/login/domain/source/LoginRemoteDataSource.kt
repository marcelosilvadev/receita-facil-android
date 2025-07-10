package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.source

import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

interface LoginRemoteDataSource{
    suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel>
}
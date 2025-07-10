package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.repository

import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
): LoginRepository
{
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return loginRemoteDataSource.login(authUserRequestModel)
    }
}
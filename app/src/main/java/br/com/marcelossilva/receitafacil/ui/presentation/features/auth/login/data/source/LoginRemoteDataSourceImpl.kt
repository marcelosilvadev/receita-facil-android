package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.source

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.domain.exceptions.ErrorResponseException
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.mappers.toAuthRequest
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.mappers.toTokenResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceApi: RecipesServiceApi
) : LoginRemoteDataSource {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return try {
            val authRequest = authUserRequestModel.toAuthRequest()
            val response = recipesServiceApi.login(authRequest)
            if (response.isSuccessful) {
                ServiceResult.Success(response.toTokenResponseModel())
            } else {
                ServiceResult.Error(message = response.message)
            }
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}
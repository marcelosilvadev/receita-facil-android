package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.data.mappers

import br.com.marcelossilva.receitafacil.core.data.remote.requests.AuthRequest
import br.com.marcelossilva.receitafacil.core.data.remote.responses.TokenResponse
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

fun AuthUserRequestModel.toAuthRequest(): AuthRequest {
    return AuthRequest(
        email,
        password
    )
}

fun TokenResponse.toTokenResponseModel(): TokenResponseModel{
    return TokenResponseModel(
       isSuccessful,
        message,
        token,
        userName
    )
}
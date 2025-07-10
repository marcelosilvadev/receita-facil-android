package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.mapper

import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddUserRequest
import br.com.marcelossilva.receitafacil.core.data.remote.responses.SimpleResponse
import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel

fun AddUserRequestModel.toAddUserRequest(): AddUserRequest {
    return AddUserRequest(
        name,
        email,
        password,
        phone
    )
}

fun SimpleResponse.toSimpleResponseModel(): SimpleResponseModel {
    return SimpleResponseModel(
        isSuccessful,
        message
    )
}
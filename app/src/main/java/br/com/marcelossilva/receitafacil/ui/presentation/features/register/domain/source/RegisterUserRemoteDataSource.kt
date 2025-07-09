package br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.source

import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.model.AddUserRequestModel

interface RegisterUserRemoteDataSource {
    suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel>
}
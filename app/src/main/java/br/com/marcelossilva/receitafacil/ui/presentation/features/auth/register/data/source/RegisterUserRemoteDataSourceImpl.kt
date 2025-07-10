package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.source

import br.com.marcelossilva.receitafacil.core.data.remote.RecipesServiceApi
import br.com.marcelossilva.receitafacil.core.domain.exceptions.ErrorResponseException
import br.com.marcelossilva.receitafacil.core.domain.model.SimpleResponseModel
import br.com.marcelossilva.receitafacil.core.util.ServiceResult
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.mapper.toAddUserRequest
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimpleResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.source.RegisterUserRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class RegisterUserRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceApi: RecipesServiceApi
) : RegisterUserRemoteDataSource {
    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel> {
        return try {
            val addUserRequest = addUserRequestModel.toAddUserRequest()
            val response = recipesServiceApi.register(addUserRequest)
            if (response.isSuccessful) {
                ServiceResult.Success(response.toSimpleResponseModel())
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
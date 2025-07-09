package br.com.marcelossilva.receitafacil.core.data.remote

import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddUserRequest
import br.com.marcelossilva.receitafacil.core.data.remote.requests.AuthRequest
import br.com.marcelossilva.receitafacil.core.data.remote.responses.SimpleResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.TokenResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.UserResponse

interface RecipesServiceApi {
    suspend fun login(authRequest: AuthRequest): TokenResponse
    suspend fun register(addUserRequest: AddUserRequest): SimpleResponse
    suspend fun getProfileUser(): UserResponse
}
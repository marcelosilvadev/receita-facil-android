package br.com.marcelossilva.receitafacil.core.data.remote

import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddUpdateRecipeRequest
import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddUserRequest
import br.com.marcelossilva.receitafacil.core.data.remote.requests.AuthRequest
import br.com.marcelossilva.receitafacil.core.data.remote.responses.RecipeDetailsResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.RecipeResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.SimpleResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.TokenResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RecipesServiceApiImpl @Inject constructor(
    private val client: HttpClient
) : RecipesServiceApi {
    override suspend fun login(authRequest: AuthRequest): TokenResponse {
        val response = client.post("users/login") {
            contentType(ContentType.Application.Json)
            setBody(authRequest)
        }
        return response.body()
    }

    override suspend fun register(addUserRequest: AddUserRequest): SimpleResponse {
        val response = client.post("users/register") {
            contentType(ContentType.Application.Json)
            setBody(addUserRequest)
        }
        return response.body()
    }

    override suspend fun getProfileUser(): UserResponse {
        val response = client.get("users/profile") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }

    override suspend fun getRecipesByUser(category: Int?): List<RecipeResponse> {
        val response = client.get("recipes") {
            contentType(ContentType.Application.Json)
            category?.let { parameter("category", it) }
        }
        return response.body()
    }

    override suspend fun searchRecipes(nameOrIngredient: String): List<RecipeResponse> {
        val response = client.get("recipes/search") {
            contentType(ContentType.Application.Json)
            parameter("nameOrIngredient", nameOrIngredient)
        }
        return response.body()
    }

    override suspend fun getRecipeById(recipeId: String): RecipeDetailsResponse {
        val response = client.get("recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }

    override suspend fun addRecipe(addUpdateRecipeRequest: AddUpdateRecipeRequest): SimpleResponse {
        val response = client.post("recipes") {
            contentType(ContentType.Application.Json)
            setBody(addUpdateRecipeRequest)
        }
        return response.body()
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequest: AddUpdateRecipeRequest
    ): SimpleResponse {
        val response = client.put("recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
            setBody(addUpdateRecipeRequest)
        }
        return response.body()
    }

    override suspend fun deleteRecipe(recipeId: String): SimpleResponse {
        val response = client.delete("recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }

}
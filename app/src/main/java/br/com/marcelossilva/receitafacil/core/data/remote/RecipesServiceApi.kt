package br.com.marcelossilva.receitafacil.core.data.remote

import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddUpdateRecipeRequest
import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddUserRequest
import br.com.marcelossilva.receitafacil.core.data.remote.requests.AuthRequest
import br.com.marcelossilva.receitafacil.core.data.remote.responses.RecipeDetailsResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.RecipeResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.SimpleResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.TokenResponse
import br.com.marcelossilva.receitafacil.core.data.remote.responses.UserResponse

interface RecipesServiceApi {
    suspend fun login(authRequest: AuthRequest): TokenResponse
    suspend fun register(addUserRequest: AddUserRequest): SimpleResponse
    suspend fun getProfileUser(): UserResponse

    suspend fun getRecipesByUser(category: Int?): List<RecipeResponse>
    suspend fun searchRecipes(nameOrIngredient: String): List<RecipeResponse>
    suspend fun getRecipeById(recipeId: String): RecipeDetailsResponse
    suspend fun addRecipe(addUpdateRecipeRequest: AddUpdateRecipeRequest): SimpleResponse
    suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequest: AddUpdateRecipeRequest
    ): SimpleResponse

    suspend fun deleteRecipe(recipeId: String): SimpleResponse
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.data.mapper

import br.com.marcelossilva.receitafacil.core.data.remote.responses.RecipeResponse
import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel

fun RecipeResponse.toRecipeResponseModel(): RecipesResponseModel {
    return RecipesResponseModel(
        id,
        name,
        category,
        ownerName,
        totalIngredients,
        preparationTime
    )
}
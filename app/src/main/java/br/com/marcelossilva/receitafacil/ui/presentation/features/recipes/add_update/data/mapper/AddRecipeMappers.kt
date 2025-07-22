package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.data.mapper

import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddIngredientRequest
import br.com.marcelossilva.receitafacil.core.data.remote.requests.AddUpdateRecipeRequest
import br.com.marcelossilva.receitafacil.core.domain.model.IngredientsModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel

fun IngredientsModel.toAddIngredientsRequestModel(): AddIngredientRequest {
    return AddIngredientRequest(
        name,
        quantity,
    )
}

fun AddUpdateRecipeRequestModel.toAddUpdateRecipeRequest(): AddUpdateRecipeRequest {
    return AddUpdateRecipeRequest(
        name,
        category,
        preparationTime = preparationTime,
        preparationMode = preparationMode,
        ingredients = ingredients.map { it.toAddIngredientsRequestModel() },
    )
}
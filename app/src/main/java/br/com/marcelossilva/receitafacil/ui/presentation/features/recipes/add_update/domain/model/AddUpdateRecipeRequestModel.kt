package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.add_update.domain.model

import br.com.marcelossilva.receitafacil.core.domain.model.IngredientsModel

data class AddUpdateRecipeRequestModel(
    val name: String,
    val category: Int,
    val preparationTime: String,
    val preparationMode: String,
    val ingredients: List<IngredientsModel> = emptyList(),
)

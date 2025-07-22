package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.state

import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel

data class RecipesUiState(
    val isEmpty: Boolean = false,
    val userName: String? = null,
    val category: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val recipes: List<RecipesResponseModel> = emptyList(),
)

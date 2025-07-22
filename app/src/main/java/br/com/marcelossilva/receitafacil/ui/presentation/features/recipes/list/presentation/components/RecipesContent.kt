package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.components.state.ErrorState
import br.com.marcelossilva.receitafacil.ui.presentation.components.state.LoadingIndicator
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun RecipesContent(
    modifier: Modifier = Modifier,
    isEmpty: Boolean,
    isLoading: Boolean,
    errorMessage: String?,
    selectedCategory: Int?,
    onSelectedCategory: (Int?) -> Unit,
    recipes: List<RecipesResponseModel>,
    onNavigateToRecipeDetailScreen: (String) -> Unit
) {
    val categories = rememberCategories()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            isLoading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxHeight()
                )
            }

            errorMessage != null -> {
                ErrorState(message = errorMessage)
            }

            isEmpty -> {
                CategoryFilterChipList(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onSelectedCategory = onSelectedCategory
                )
                RecipesEmptySate(
                    modifier = Modifier.padding(12.dp)
                )
            }

            else -> {
                CategoryFilterChipList(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onSelectedCategory = onSelectedCategory
                )
                RecipesItemList(
                    recipesResponseModel = recipes,
                    onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListRecipeContentPreview() {
    ReceitaFacilAppTheme {
        RecipesContent(
            modifier = Modifier,
            isEmpty = false,
            isLoading = false,
            errorMessage = null,
            selectedCategory = null,
            onSelectedCategory = {},
            recipes = listOf(
                RecipesResponseModel(
                    id = "1",
                    name = "Receita de Teste",
                    category = "Test",
                    ownerName = "Lucão",
                    preparationTime = 30,
                    totalIngredients = 5
                )
            ),
            onNavigateToRecipeDetailScreen = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ListRecipeContentLoadingPreview() {
    ReceitaFacilAppTheme {
        RecipesContent(
            modifier = Modifier,
            isEmpty = false,
            isLoading = true,
            errorMessage = null,
            selectedCategory = null,
            onSelectedCategory = {},
            recipes = listOf(),
            onNavigateToRecipeDetailScreen = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ListRecipeContentErrorPreview() {
    ReceitaFacilAppTheme {
        RecipesContent(
            modifier = Modifier,
            isEmpty = false,
            isLoading = false,
            errorMessage = "Erro na tela",
            selectedCategory = null,
            onSelectedCategory = {},
            recipes = listOf(),
            onNavigateToRecipeDetailScreen = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ListRecipeContentEmptyPreview() {
    ReceitaFacilAppTheme {
        RecipesContent(
            modifier = Modifier,
            isEmpty = true,
            isLoading = false,
            errorMessage = null,
            selectedCategory = null,
            onSelectedCategory = {},
            recipes = listOf(),
            onNavigateToRecipeDetailScreen = {}
        )
    }
}
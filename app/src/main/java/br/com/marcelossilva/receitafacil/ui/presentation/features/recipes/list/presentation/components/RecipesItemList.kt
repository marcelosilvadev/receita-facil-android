package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marcelossilva.receitafacil.core.domain.model.RecipesResponseModel
import br.com.marcelossilva.receitafacil.ui.presentation.components.recipes.RecipesItem
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun RecipesItemList(
    modifier: Modifier = Modifier,
    recipesResponseModel: List<RecipesResponseModel>,
    onNavigateToRecipeDetailScreen: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp),
        content = {
            items(
                items = recipesResponseModel,
                key = { item -> item.id }
            ) { recipe ->
                RecipesItem(
                    recipes = recipe,
                    onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun RecipesItemListPreview() {
    ReceitaFacilAppTheme {
        RecipesItemList(
            modifier = Modifier,
            recipesResponseModel = listOf(
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
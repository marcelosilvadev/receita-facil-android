package br.com.marcelossilva.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesScreen
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesViewModel
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.recipesScreen(
    onNavigationToAuthGraph: () -> Unit,
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
    onNavigationToRecipeDetailsScreen: (recipeId: String) -> Unit,
) {
    composable<HomeScreens.ListRecipesScreen> {
        val viewModel: RecipesViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel
        RecipesScreen(
            state = uiState,
            selectedCategory = selectedCategory,
            onLogout = {
                viewModel.logout()
                onNavigationToAuthGraph()
            },
            onSelectedCategory = { viewModel.selectedCategory(it) },
            onNavigationToProfileScreen = onNavigationToProfileScreen,
            onNavigationToSearchScreen = onNavigationToSearchScreen,
            onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen,
            onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen,
            onNavigationToRecipeDetailsScreen = onNavigationToRecipeDetailsScreen,
        )
    }
}
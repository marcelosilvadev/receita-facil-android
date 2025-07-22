package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.marcelossilva.receitafacil.ui.presentation.components.bottombar.BottomBar
import br.com.marcelossilva.receitafacil.ui.presentation.components.topbar.CommonTopBar
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.components.RecipesContent
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.state.RecipesUiState

@Composable
fun RecipesScreen(
    state: RecipesUiState,
    selectedCategory: Int?,
    onLogout: () -> Unit,
    onSelectedCategory: (Int?) -> Unit,
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
    onNavigationToRecipeDetailsScreen: (recipeId: String) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                title = "Olá, ${state.userName}",
                actionImageVector = Icons.AutoMirrored.Outlined.Logout,
                onActionIconButton = { onLogout() }
            )
        },
        bottomBar = {
            BottomBar(
                onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen,
                onNavigationToSearchScreen = onNavigationToSearchScreen,
                onNavigationToProfileScreen = onNavigationToProfileScreen,
                onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                RecipesContent(
                    modifier = Modifier.fillMaxSize(),
                    isEmpty = state.isEmpty,
                    isLoading = state.isLoading,
                    errorMessage = state.errorMessage,
                    recipes = state.recipes,
                    selectedCategory = selectedCategory,
                    onSelectedCategory = { onSelectedCategory(it) },
                    onNavigateToRecipeDetailScreen = onNavigationToRecipeDetailsScreen
                )
            }

        }
    )
}
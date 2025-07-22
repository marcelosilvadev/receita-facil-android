package br.com.marcelossilva.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.recipesScreen
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.Graphs
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph(
    onNavigateUp: () -> Unit,
    onNavigationToAuthGraph: () -> Unit,
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
    onNavigationToRecipeDetailsScreen: (recipeId: String) -> Unit,
) {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.ListRecipesScreen
    ) {
        recipesScreen(
            onNavigationToAuthGraph = onNavigationToAuthGraph,
            onNavigationToProfileScreen = onNavigationToProfileScreen,
            onNavigationToSearchScreen = onNavigationToSearchScreen,
            onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen,
            onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen,
            onNavigationToRecipeDetailsScreen = onNavigationToRecipeDetailsScreen,
        )
    }
}

fun NavController.navigationToHomeGraph(
    navOptions: NavOptions? = null
) {
    navigate(Graphs.HomeGraph, navOptions)
}
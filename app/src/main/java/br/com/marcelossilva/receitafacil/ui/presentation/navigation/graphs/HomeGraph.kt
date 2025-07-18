package br.com.marcelossilva.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.listRecipesScreen
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.Graphs
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph(
    onNavigateUp: () -> Unit,
) {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.ListRecipesScreen
    ) {
        listRecipesScreen()
    }
}

fun NavController.navigationToHomeGraph(
    navOptions: NavOptions? = null
) {
    navigate(Graphs.HomeGraph, navOptions)
}
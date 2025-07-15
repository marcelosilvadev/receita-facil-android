package br.com.marcelossilva.receitafacil.ui.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.ListRecipesScreen
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.ListRecipesViewModel
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.listRecipesScreen() {
    composable<HomeScreens.ListRecipesScreen> {
        val viewModel: ListRecipesViewModel = hiltViewModel()
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//        val sideEffectFlow = viewModel.sideEffectChannel
        ListRecipesScreen()
    }
}
package br.com.marcelossilva.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.graphs.authGraph
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.graphs.homeGraph
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.Graphs

@Composable
fun RootHost(
    startDestination: Graphs,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        authGraph(
            onNavigateToHomeGraph = {
                navController.navigate(Graphs.HomeGraph)
            },
            onNavigateToRegisterScreen = {
                navController.navigateToRegisterScreen()
            },
            onNavigateToLoginScreen = {
                navController.navigateToLoginScreen()
            }

        )
        homeGraph(
            onNavigateUp = {
                navController.navigateUp()
            }
        )

    }

}
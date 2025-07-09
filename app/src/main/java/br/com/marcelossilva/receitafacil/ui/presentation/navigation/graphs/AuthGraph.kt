package br.com.marcelossilva.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.loginScreen
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.registerScreen
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.AuthScreens
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToHomeGraph: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ){
        loginScreen(
            onNavigateToHome = onNavigateToHomeGraph,
            onNavigateToRegisterScreen = onNavigateToRegisterScreen
        )
        registerScreen(
            onNavigateToLoginScreen = onNavigateToLoginScreen
        )
    }
}
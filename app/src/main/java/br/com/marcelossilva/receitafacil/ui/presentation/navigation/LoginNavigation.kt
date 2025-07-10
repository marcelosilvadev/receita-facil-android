package br.com.marcelossilva.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.LoginScreen
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.LoginViewModel
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.loginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit
) {
    composable<AuthScreens.LoginScreen> {
        val viewModel: LoginViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel
        LoginScreen (
            uiState = uiState,
            sideEffectFlow = sideEffectFlow,
            onEvent = viewModel::onEvent,
            onNavigateToRegisterScreen = onNavigateToRegisterScreen,
            onNavigateToHomeGraph = onNavigateToHome,
        )
    }
}

fun NavController.navigateToLoginScreen() {
    navigate(AuthScreens.LoginScreen) {
        popUpTo(0) //Limpa pilha de navegação
    }
}
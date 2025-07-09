package br.com.marcelossilva.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.presentation.RegisterScreen
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.presentation.RegisterUserViewModel
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.registerScreen(
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<AuthScreens.RegisterScreen> {
        val viewModel: RegisterUserViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel

        RegisterScreen(
            uiState = uiState,
            sideEffectFlow = sideEffectFlow,
            onEvent = { viewModel.onEvent(it) },
            onNameChanged={viewModel.onNameInputChange(it)},
            onEmailChanged={viewModel.onEmailInputChange(it)},
            onPhoneChanged={viewModel.onPhoneNumberInputChange(it)},
            onPasswordChanged={viewModel.onPasswordInputChange(it)},
            onConfirmPasswordChanged={viewModel.onConfirmPasswordInputChange(it)},
            onToggleShowPassword={viewModel.onToggleShowPassword()},
            onToggleShowConfirmPassword={viewModel.onToggleShowConfirmPassword()},
            onNavigateToLoginScreen={onNavigateToLoginScreen()},
        )
    }
}

fun NavController.navigateToRegisterScreen(){
    navigate(AuthScreens.RegisterScreen)
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import br.com.marcelossilva.receitafacil.core.sideeffects.SideEffect
import br.com.marcelossilva.receitafacil.core.util.SingleEventEffect
import br.com.marcelossilva.receitafacil.core.util.extensions.toast
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.presentation.components.RegisterContent
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.presentation.state.RegisterUserState
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun RegisterScreen(
    uiState: RegisterUserState,
    sideEffectFlow: Flow<SideEffect>,
    onEvent: (RegisterUserEvent) -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onToggleShowPassword: () -> Unit,
    onToggleShowConfirmPassword: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,

    ) {
    val context = LocalContext.current
    SingleEventEffect(sideEffectFlow) { sideEffect ->
        when(sideEffect){
            is SideEffect.ShowToast -> context.toast(sideEffect.message)
            else -> {}
        }
    }

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isSuccess
        },
        destination = {
            onNavigateToLoginScreen()
        }
    )

    Scaffold(
        content = { paddingValues ->
            RegisterContent(
                paddingValues = paddingValues,
                uiState = uiState,
                onRegisterClick = {onEvent(RegisterUserEvent.OnRegisterClick)},
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onConfirmPasswordChanged = onConfirmPasswordChanged,
                onNavigateToLoginScreen = onNavigateToLoginScreen,
                onToggleShowPassword = onToggleShowPassword,
                onToggleShowConfirmPassword = onToggleShowConfirmPassword
            )
        }
    )
}
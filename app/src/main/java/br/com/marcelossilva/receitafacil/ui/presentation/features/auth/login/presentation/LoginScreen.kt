package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.marcelossilva.receitafacil.core.sideeffects.SideEffect
import br.com.marcelossilva.receitafacil.core.util.SingleEventEffect
import br.com.marcelossilva.receitafacil.core.util.extensions.toast
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.components.LoginContent
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    sideEffectFlow: Flow<SideEffect>,
    onEvent: (LoginEvent) -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToHomeGraph: () -> Unit,
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
           uiState.isSuccessLogin
        },
        destination = {
            onNavigateToHomeGraph()
        }
    )

    Scaffold(
        content = {paddingValues ->
            LoginContent(
                modifier = Modifier,
                paddingValues = paddingValues,
                uiState = uiState,
                onLoginClick = { onEvent(LoginEvent.OnLoginClick) },
                onEmailChange = {onEvent(LoginEvent.OnEmailChange(it))},
                onPasswordChange = {onEvent(LoginEvent.OnPasswordChange(it))},
                onToggleVisualTransformationPassword = {onEvent(LoginEvent.OnToggleVisualTransformationPassword)},
                onNavigateToRegisterScreen = onNavigateToRegisterScreen,
            )
        }
    )
}
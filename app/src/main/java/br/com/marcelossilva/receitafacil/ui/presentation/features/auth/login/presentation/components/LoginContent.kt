package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.marcelossilva.receitafacil.R
import br.com.marcelossilva.receitafacil.ui.presentation.components.iconapp.IconApp
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme
import br.com.marcelossilva.receitafacil.ui.theme.poppinsFontFamily

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    uiState: LoginUiState,
    onLoginClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        IconApp(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp)
        )
        Column(
            modifier= Modifier
                .padding(top = 12.dp,)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.login_text),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.errorMessageLoginProcess ?: uiState.errorMessageInput.orEmpty(),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
            LoginContainer(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                isLoading = uiState.isLoading,
                emailValue = uiState.emailValue,
                passwordValue = uiState.passwordValue,
                buttonEnabled = uiState.buttonEnabled,
                isPasswordShow = uiState.isPasswordShow,
                onEmailChanged = onEmailChange,
                onPasswordChanged = onPasswordChange,
                onLoginButtonClick = onLoginClick,
                onToggleVisualTransformationPassword = onToggleVisualTransformationPassword,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.no_have_account_text),
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = stringResource(R.string.register_text),
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            onNavigateToRegisterScreen()
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    ReceitaFacilAppTheme {
        LoginContent(
            paddingValues = PaddingValues(),
            uiState = LoginUiState(),
            onLoginClick = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToRegisterScreen = {},
            onToggleVisualTransformationPassword = {}
        )
    }
}
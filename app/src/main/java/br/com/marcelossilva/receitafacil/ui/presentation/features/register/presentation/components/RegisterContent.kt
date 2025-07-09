package br.com.marcelossilva.receitafacil.ui.presentation.features.register.presentation.components

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
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.presentation.state.RegisterUserState
import br.com.marcelossilva.receitafacil.ui.theme.poppinsFontFamily

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    uiState: RegisterUserState,
    paddingValues: PaddingValues,
    onRegisterClick: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onToggleShowPassword: () -> Unit,
    onToggleShowConfirmPassword: () -> Unit,
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconApp(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 16.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.register_text),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = uiState.errorMessageRegisterProcess
                        ?: uiState.errorMessageInput.orEmpty(),
                    fontFamily = poppinsFontFamily,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )
            }
            RegisterContainer(
                isLoading = uiState.isLoading,
                nameValue = uiState.nameValue,
                emailValue = uiState.emailValue,
                phoneValue = uiState.phoneNumberValue,
                passwordValue = uiState.passwordValue,
                confirmPasswordValue = uiState.confirmPasswordValue,
                isPasswordShown = uiState.isPasswordShow,
                buttonEnabled = uiState.isInputValid,
                isConfirmPasswordShown = uiState.isConfirmPasswordShow,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onConfirmPasswordChanged = onConfirmPasswordChanged,
                onButtonClick = onRegisterClick,
                onTrailingPasswordIconClick = onToggleShowPassword,
                onTrailingConfirmPasswordIconClick = onToggleShowConfirmPassword,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = stringResource(R.string.login_text),
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            onNavigateToLoginScreen()
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContentPreview() {
        RegisterContent(
            uiState = RegisterUserState(),
            paddingValues = PaddingValues(16.dp),
            onRegisterClick = { },
            onNameChanged = {  },
            onEmailChanged = {  },
            onPhoneChanged = { },
            onPasswordChanged = {  },
            onConfirmPasswordChanged = {  },
            onToggleShowPassword = {  },
            onToggleShowConfirmPassword = {  },
            onNavigateToLoginScreen = {  }
        )
}
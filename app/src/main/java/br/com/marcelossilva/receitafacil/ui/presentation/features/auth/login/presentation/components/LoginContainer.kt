package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marcelossilva.receitafacil.R
import br.com.marcelossilva.receitafacil.ui.presentation.components.button.AuthButton
import br.com.marcelossilva.receitafacil.ui.presentation.components.textfield.TextEntryModule
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    emailValue: String,
    passwordValue: String,
    buttonEnabled: Boolean,
    isPasswordShow: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextEntryModule(
            description = stringResource(R.string.description_email_text),
            hint = stringResource(R.string.hint_email_text),
            leadingIcon = Icons.Default.Email,
            textValue = emailValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            onValueChanged = onEmailChanged,
        )
        TextEntryModule(
            description = stringResource(R.string.description_password_text),
            hint = stringResource(R.string.hint_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            onValueChanged = onPasswordChanged,
            trailingIcon =
                if (isPasswordShow)
                    Icons.Default.RemoveRedEye
                else
                    Icons.Default.AcUnit,
            onTrailingIconClick = {
                onToggleVisualTransformationPassword()
            },
            visualTransformation = if (isPasswordShow) {
                VisualTransformation.None
            } else PasswordVisualTransformation()
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                text = stringResource(R.string.login_text),
                contentColor = Color.White,
                onButtonClick = onLoginButtonClick,
                enabled = buttonEnabled,
                isLoading = isLoading
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    ReceitaFacilAppTheme {
        LoginContainer(
            modifier = Modifier,
            isLoading = false,
            emailValue = "",
            passwordValue = "",
            buttonEnabled = false,
            isPasswordShow = true,
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginButtonClick = {},
            onToggleVisualTransformationPassword = {},
        )
    }
}
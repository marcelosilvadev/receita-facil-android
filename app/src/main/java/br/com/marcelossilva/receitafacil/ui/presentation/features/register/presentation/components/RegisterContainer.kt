package br.com.marcelossilva.receitafacil.ui.presentation.features.register.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import br.com.marcelossilva.receitafacil.ui.presentation.components.transformations.PhoneVisualTransformation
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun RegisterContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    nameValue: String,
    emailValue: String,
    phoneValue: String,
    passwordValue: String,
    confirmPasswordValue: String,
    isPasswordShown: Boolean,
    buttonEnabled: Boolean,
    isConfirmPasswordShown: Boolean,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onTrailingPasswordIconClick: () -> Unit,
    onTrailingConfirmPasswordIconClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_nome_text),
            hint = stringResource(R.string.hint_nome_text),
            leadingIcon = Icons.Default.Person,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            textValue = nameValue,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = onNameChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_email_text),
            hint = stringResource(R.string.hint_email_text),
            leadingIcon = Icons.Default.Email,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            textValue = emailValue,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = onEmailChanged,
            keyboardType = KeyboardType.Email,
            trailingIcon = null,
            onTrailingIconClick = null,
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_phone_text),
            hint = stringResource(R.string.hint_phone_text),
            leadingIcon = Icons.Default.Phone,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            textValue = phoneValue,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = {
                if(it.length <= 11){
                    onPhoneChanged(it)
                }
            },
            keyboardType = KeyboardType.Phone,
            visualTransformation = PhoneVisualTransformation(),
            trailingIcon = null,
            onTrailingIconClick = null,
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_password_text),
            hint = stringResource(R.string.hint_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            textValue = passwordValue,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            onValueChanged = onPasswordChanged,
            visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onTrailingPasswordIconClick,
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_repeat_password_text),
            hint = stringResource(R.string.hint_repeat_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            textValue = confirmPasswordValue,
            keyboardType = KeyboardType.Password,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Done,
            onValueChanged = onConfirmPasswordChanged,
            visualTransformation = if (isConfirmPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onTrailingConfirmPasswordIconClick,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                text = stringResource(R.string.register_button_text),
                contentColor = Color.White,
                isLoading = isLoading,
                enabled = buttonEnabled,
                onButtonClick = onButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContainerPreview() {
    ReceitaFacilAppTheme {
        RegisterContainer(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            nameValue = "",
            emailValue = "",
            phoneValue = "",
            passwordValue = "",
            confirmPasswordValue = "",
            buttonEnabled = true,
            onNameChanged = {},
            onEmailChanged = {},
            onPhoneChanged = {},
            onPasswordChanged = {},
            onConfirmPasswordChanged = {},
            onButtonClick = { /*TODO*/ },
            isPasswordShown = false,
            isConfirmPasswordShown = false,
            onTrailingPasswordIconClick = { /*TODO*/ },
            onTrailingConfirmPasswordIconClick = { /*TODO*/ },
            isLoading = false
        )
    }

}
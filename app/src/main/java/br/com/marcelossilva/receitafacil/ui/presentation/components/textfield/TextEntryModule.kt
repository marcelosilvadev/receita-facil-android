package br.com.marcelossilva.receitafacil.ui.presentation.components.textfield


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme
import br.com.marcelossilva.receitafacil.ui.theme.poppinsFontFamily

@Composable
fun TextEntryModule(
    modifier: Modifier = Modifier,
    description: String,
    hint: String,
    textColor: Color,
    cursorColor: Color,
    leadingIcon: ImageVector,
    textValue: String,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            textStyle = TextStyle(
                fontFamily = poppinsFontFamily
            ),
            onValueChange = onValueChanged,
            label = {
                Text(
                    text = description,
                    color = textColor,
                    fontFamily = poppinsFontFamily
                )
            },
            placeholder = {
                Text(
                    text = hint,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = poppinsFontFamily
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Icone do text field",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "Icone de ação",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .clickable {
                                if(onTrailingIconClick != null){
                                    onTrailingIconClick()
                                }
                            }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = cursorColor,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
            )
        )
    }
}

@Preview
@Composable
private fun TextEntryModulePreview() {
    ReceitaFacilAppTheme {
        TextEntryModule(
            description = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 5.dp),
            hint = "teste@gmail.com",
            leadingIcon = Icons.Default.Email,
            textValue = "123456",
            textColor = Color.Black,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            onValueChanged = {},
            trailingIcon = Icons.Filled.RemoveRedEye,
            onTrailingIconClick = {},
            visualTransformation = PasswordVisualTransformation()
        )
    }

}
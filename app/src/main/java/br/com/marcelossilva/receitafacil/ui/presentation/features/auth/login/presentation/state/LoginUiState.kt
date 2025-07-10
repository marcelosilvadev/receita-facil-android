package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.state

data class LoginUiState(
    val emailValue: String = "",
    val passwordValue: String = "",
    val buttonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val errorMessageInput: String? = null,
    val isSuccessLogin: Boolean = false,
    val errorMessageLoginProcess: String? = null,
)

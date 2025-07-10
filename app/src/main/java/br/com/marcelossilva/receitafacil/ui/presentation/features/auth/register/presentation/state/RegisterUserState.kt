package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.presentation.state

data class RegisterUserState(
    val nameValue: String = "",
    val emailValue: String = "",
    val phoneNumberValue: String = "",
    val passwordValue: String = "",
    val confirmPasswordValue: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val isConfirmPasswordShow: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessageRegisterProcess: String? = null
)
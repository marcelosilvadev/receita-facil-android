package br.com.marcelossilva.receitafacil.ui.presentation.features.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marcelossilva.receitafacil.core.sideeffects.SideEffect
import br.com.marcelossilva.receitafacil.core.util.Constants
import br.com.marcelossilva.receitafacil.core.util.extensions.observeState
import br.com.marcelossilva.receitafacil.core.util.extensions.toFormattedPhoneNumber
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.model.AddUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.model.RegisterInputValidationType
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.register.presentation.state.RegisterUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(RegisterUserState())
    var uiState = _uiState.asStateFlow()

    //Cria um canal que pode enviar objetos do tipo SideEffect
    //O Canal é Buffered, o que significa que pode armazenar múltiplos SideEffects
    //sem bloquear imediatamente quem envia
    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    //Expoe o canal como um Flow, permitindo que a UI "colecione" os efeitos colaterais
    //de forma reativa, usando o padrão do Kotlin Flow
    var sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onEvent(event: RegisterUserEvent) {
        when(event){
            is RegisterUserEvent.OnRegisterClick -> onRegisterClick()
        }
    }

    fun onNameInputChange(newValue: String) {
        _uiState.update{ it.copy(nameValue = newValue)}
        checkedInputValidation()
    }

    fun onEmailInputChange(newValue: String) {
        _uiState.update{ it.copy(emailValue = newValue)}
        checkedInputValidation()
    }

    fun onPhoneNumberInputChange(newValue: String) {
        _uiState.update{ it.copy(phoneNumberValue = newValue)}
        checkedInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        _uiState.update{ it.copy(passwordValue = newValue)}
        checkedInputValidation()
    }

    fun onConfirmPasswordInputChange(newValue: String) {
        _uiState.update{ it.copy(confirmPasswordValue = newValue)}
        checkedInputValidation()
    }

    fun onToggleShowPassword() {
        _uiState.update{ it.copy(isPasswordShow = !it.isPasswordShow)}
    }

    fun onToggleShowConfirmPassword() {
        _uiState.update{ it.copy(isConfirmPasswordShow = !it.isConfirmPasswordShow)}
    }

    private fun checkedInputValidation(){
        val validationResult = validateRegisterInputUseCase(
            name = _uiState.value.nameValue,
            email = _uiState.value.emailValue,
            phone = _uiState.value.phoneNumberValue,
            password = _uiState.value.passwordValue,
            confirmPassword = _uiState.value.confirmPasswordValue
        )
        processInputValidationType(validationResult)
    }

    private fun onRegisterClick(){
        viewModelScope.launch {
            registerUserUseCase.invoke(
                parameters = RegisterUserUseCase.Parameters(
                    AddUserRequestModel(
                        name = _uiState.value.nameValue,
                        email = _uiState.value.emailValue,
                        phone = _uiState.value.phoneNumberValue.toFormattedPhoneNumber(),
                        password = _uiState.value.passwordValue,
                    )
                )
            ).observeState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = response.isSuccessful,
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message))
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageRegisterProcess = error.message.toString()
                        )
                    }
                }
            )
        }
    }

    private fun processInputValidationType(type: RegisterInputValidationType) {
        _uiState.update {
            when(type){
                RegisterInputValidationType.EmptyField -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.EMPTY_FIELD
                    )
                }
                RegisterInputValidationType.NoEmail -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.INVALID_EMAIL
                    )
                }
                RegisterInputValidationType.PasswordTooShort -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_TOO_SHORT
                    )
                }
                RegisterInputValidationType.PasswordsDoNotMatch -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORDS_DO_NOT_MATCH
                    )
                }
                RegisterInputValidationType.PasswordSpecialCharMissing -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_SPECIAL_CHAR_MISSING
                    )
                }
                RegisterInputValidationType.PasswordNumberMissing -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_NUMBER_MISSING
                    )
                }
                RegisterInputValidationType.PasswordUpperCaseMissing -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_UPPERCASE_MISSING
                    )
                }
                RegisterInputValidationType.PhoneNumberInvalid -> {
                    it.copy(
                        isInputValid = false,
                        errorMessageInput = Constants.ValidationAuthMessages.PHONE_NUMBER_INVALID
                    )
                }
                RegisterInputValidationType.Valid -> {
                    it.copy(
                        isInputValid = true,
                        errorMessageInput = null
                    )
                }
            }

        }
    }
}
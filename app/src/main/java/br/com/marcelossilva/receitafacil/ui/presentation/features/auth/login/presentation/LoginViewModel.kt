package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marcelossilva.receitafacil.core.sideeffects.SideEffect
import br.com.marcelossilva.receitafacil.core.util.Constants
import br.com.marcelossilva.receitafacil.core.util.extensions.observeState
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.LoginInputValidationType
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val validateLoginInputUseCase: ValidateLoginInputUseCase,
    val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState());
    val uiState = _uiState.asStateFlow();

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                _uiState.value = _uiState.value.copy(emailValue = event.email)
                checkInputValidation()
            }

            is LoginEvent.OnPasswordChange -> {
                _uiState.value = _uiState.value.copy(passwordValue = event.password)
                checkInputValidation()
            }

            LoginEvent.OnToggleVisualTransformationPassword -> {
                _uiState.value =
                    _uiState.value.copy(isPasswordShow = !_uiState.value.isPasswordShow)
            }

            LoginEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onLoginClick() {
        viewModelScope.launch {
            loginUseCase.invoke(
                parameters = LoginUseCase.Parameters(
                    authUserRequestModel = AuthUserRequestModel(
                        email = _uiState.value.emailValue,
                        password = _uiState.value.passwordValue
                    )
                )
            ).observeState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onSuccess = { response ->
                    _uiState.update { it.copy(isLoading = false, isSuccessLogin = true) }
                    _sideEffectChannel.send(
                        SideEffect.ShowToast(
                            message = response.message.toString()
                        )
                    )
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageLoginProcess = error.message
                        )
                    }
                }
            )
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            email = _uiState.value.emailValue,
            password = _uiState.value.passwordValue
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType) {
        _uiState.update {
            when (type) {
                LoginInputValidationType.EmptyField -> it.copy(
                    errorMessageInput = Constants.ValidationAuthMessages.EMPTY_FIELD,
                    buttonEnabled = false,
                    isInputValid = false
                )

                LoginInputValidationType.NoEmail -> it.copy(
                    errorMessageInput = Constants.ValidationAuthMessages.INVALID_EMAIL,
                    buttonEnabled = false,
                    isInputValid = false
                )

                LoginInputValidationType.Valid -> it.copy(
                    errorMessageInput = null,
                    buttonEnabled = true,
                    isInputValid = true
                )
            }
        }
    }

    private fun saveLocalStorageData(token: String, userName: String) {}
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.lifecycle.ViewModel
import br.com.marcelossilva.receitafacil.core.sideeffects.SideEffect
import br.com.marcelossilva.receitafacil.core.util.Constants
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.LoginInputValidationType
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val validateLoginInputUseCase: ValidateLoginInputUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState());
    val uiState = _uiState.asStateFlow();

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.OnEmailChange -> {
                _uiState.value = _uiState.value.copy(emailValue = event.email)
                checkInputValidation()
            }
            is LoginEvent.OnPasswordChange -> {
                _uiState.value = _uiState.value.copy(passwordValue = event.password)
                checkInputValidation()
            }
            LoginEvent.OnToggleVisualTransformationPassword -> {
                _uiState.value = _uiState.value.copy(isPasswordShow = !_uiState.value.isPasswordShow)
            }
            LoginEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onLoginClick(){
        TODO("Implement login logic here")
    }

    private fun checkInputValidation(){
        val validationResult = validateLoginInputUseCase(
            email = _uiState.value.emailValue,
            password = _uiState.value.passwordValue
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType){
        _uiState.update {
            when(type){
                LoginInputValidationType.EmptyField -> it.copy(errorMessageInput = Constants.ValidationAuthMessages.EMPTY_FIELD, isInputValid = false)
                LoginInputValidationType.NoEmail -> it.copy(errorMessageInput = Constants.ValidationAuthMessages.INVALID_EMAIL, isInputValid = false)
                LoginInputValidationType.Valid -> it.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }

    private fun saveLocalStorageData(token: String, userName: String){}
}
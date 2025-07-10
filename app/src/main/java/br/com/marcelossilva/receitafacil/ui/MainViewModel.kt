package br.com.marcelossilva.receitafacil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marcelossilva.receitafacil.core.util.logging.logInfo
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.Graphs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _isSplashLoading = MutableStateFlow(true)
    val isSplashLoading = _isSplashLoading.asStateFlow()

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getUserDataUseCase.invoke().collect { userData ->
                if (userData.token.isNotEmpty()) {
                    _uiState.update {
                        it.copy(startDestination = Graphs.HomeGraph)
                    }
                } else if (!userData.errorMessage.isNullOrEmpty()) {
                    logInfo("USER_DATA", "UserData: ${userData.errorMessage}")
                }
                delay(1000)
                _isSplashLoading.update { false }
            }
        }
    }

}
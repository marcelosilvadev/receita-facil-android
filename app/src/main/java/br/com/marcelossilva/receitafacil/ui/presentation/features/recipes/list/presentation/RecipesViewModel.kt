package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marcelossilva.receitafacil.core.sideeffects.SideEffect
import br.com.marcelossilva.receitafacil.core.util.extensions.observeState
import br.com.marcelossilva.receitafacil.core.util.logging.logInfo
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.domain.usecase.GetRecipesByUserUseCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.state.RecipesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesByUserUseCase: GetRecipesByUserUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val removeUserDataUseCase: RemoveUserDataUseCase
) : ViewModel() {

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    private val _selectedCategory = MutableStateFlow<Int?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _uiState = MutableStateFlow(RecipesUiState())
    val uiState: StateFlow<RecipesUiState> = _uiState.onStart {
        fetchRecipes(null)
        fetchUserName()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecipesUiState()
    )

    fun selectedCategory(category: Int?) {
        _selectedCategory.value = category
        fetchRecipes(_selectedCategory.value)
    }

    private fun fetchRecipes(category: Int?) {
        viewModelScope.launch {
            getRecipesByUserUseCase(
                parameters = GetRecipesByUserUseCase.Parameters(category = category)
            ).observeState(
                onLoading = {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                            isEmpty = false,
                            errorMessage = null
                        )
                    }
                },
                onEmpty = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isEmpty = true,
                        )
                    }
                },
                onSuccess = { recipes ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isEmpty = false,
                            recipes = recipes,
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isEmpty = false,
                            errorMessage = error.message
                        )
                    }
                },

                )
        }
    }

    private fun fetchUserName() {
        viewModelScope.launch {
            getUserDataUseCase.invoke().collect { userData ->
                _uiState.update { it.copy(userName = userData.userName) }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            removeUserDataUseCase.invoke()
                .observeState(
                    onLoading = {},
                    onFailure = {},
                    onSuccess = {
                        logInfo("REMOVE_TOKEN", "User data removed successfully")
                    }
                )
        }
    }
}
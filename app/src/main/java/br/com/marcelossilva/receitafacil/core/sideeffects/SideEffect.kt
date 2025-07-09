package br.com.marcelossilva.receitafacil.core.sideeffects

sealed interface SideEffect {
    data class ShowToast(val message: String) : SideEffect

    data class ShowSnackBar(val message: String) : SideEffect

    data class NavigateTo(val route: String) : SideEffect

    data class NavigateToWithArgs(val route: String, val args: Map<String, Any>) : SideEffect

    object PopBackStack : SideEffect

    object DismissDialog : SideEffect
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.presentation

sealed class RegisterUserEvent{
    data object OnRegisterClick: RegisterUserEvent()
}
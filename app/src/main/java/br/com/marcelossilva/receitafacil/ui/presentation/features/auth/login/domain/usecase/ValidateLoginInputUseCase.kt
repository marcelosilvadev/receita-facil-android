package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.usecase

import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model.LoginInputValidationType

interface ValidateLoginInputUseCase {
    operator fun invoke(
        email: String,
        password: String
    ): LoginInputValidationType
}

class ValidateLoginInputUseCaseImpl : ValidateLoginInputUseCase{
    override fun invoke(email: String, password: String): LoginInputValidationType {
        if(email.isBlank() || password.isBlank()) {
            return LoginInputValidationType.EmptyField
        }
        if("@" !in email || "." !in email) {
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }
}
package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.usecase

import br.com.marcelossilva.receitafacil.core.util.extensions.containsNumber
import br.com.marcelossilva.receitafacil.core.util.extensions.containsSpecialChar
import br.com.marcelossilva.receitafacil.core.util.extensions.containsUpperCase
import br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.model.RegisterInputValidationType
import javax.inject.Inject

interface ValidateRegisterInputUseCase {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String
    ): RegisterInputValidationType
}

class ValidateRegisterInputUseCaseImpl @Inject constructor() : ValidateRegisterInputUseCase {

    override fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String
    ): RegisterInputValidationType {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return RegisterInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return RegisterInputValidationType.NoEmail
        }
        if (password != confirmPassword) {
            return RegisterInputValidationType.PasswordsDoNotMatch
        }
        if (password.count() < 8) {
            return RegisterInputValidationType.PasswordTooShort
        }
        if (phone.count() < 11) {
            return RegisterInputValidationType.PhoneNumberInvalid
        }
        if (!password.containsNumber()) {
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if (!password.containsUpperCase()) {
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if (!password.containsSpecialChar()) {
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        return RegisterInputValidationType.Valid
    }
}
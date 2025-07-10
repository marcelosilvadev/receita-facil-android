package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.register.domain.model

enum class RegisterInputValidationType {
    EmptyField,
    NoEmail,
    PasswordsDoNotMatch,
    PasswordUpperCaseMissing,
    PasswordNumberMissing,
    PasswordSpecialCharMissing,
    PasswordTooShort,
    PhoneNumberInvalid,
    Valid
}
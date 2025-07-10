package br.com.marcelossilva.receitafacil.ui.presentation.features.auth.login.domain.model

data class TokenResponseModel(
    val isSuccessful: Boolean,
    val message: String? = null,
    val token: String? = null,
    val userName: String? = null,
)

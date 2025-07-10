package br.com.marcelossilva.receitafacil.core.domain.model

data class UserData(
    val token: String = "",
    val userName: String = "",
    val errorMessage: String? = null,
)

package br.com.marcelossilva.receitafacil.core.data.remote.requests

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)

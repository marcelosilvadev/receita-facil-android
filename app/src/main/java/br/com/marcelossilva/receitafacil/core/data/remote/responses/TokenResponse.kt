package br.com.marcelossilva.receitafacil.core.data.remote.responses

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("isSuccessful")
    val isSuccessful: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: Boolean,
    @SerializedName("userName")
    val userName: String,
)

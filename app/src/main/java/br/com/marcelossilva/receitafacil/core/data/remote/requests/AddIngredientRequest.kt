package br.com.marcelossilva.receitafacil.core.data.remote.requests

import com.google.gson.annotations.SerializedName

data class AddIngredientRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Int,
)

package br.com.marcelossilva.receitafacil.core.data.remote.responses

import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Int,
)

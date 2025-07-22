package br.com.marcelossilva.receitafacil.core.data.remote.requests

import com.google.gson.annotations.SerializedName

data class AddUpdateRecipeRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: Int,
    @SerializedName("preparationTime")
    val preparationTime: String,
    @SerializedName("preparationMode")
    val preparationMode: String,
    @SerializedName("ingredients")
    val ingredients: List<AddIngredientRequest> = listOf(),
)

package br.com.marcelossilva.receitafacil.core.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RecipeDetailsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("preparationTime")
    val preparationTime: Int,
    @SerializedName("preparationMode")
    val preparationMode: Int,
    @SerializedName("createdAt")
    val createdAt: Int,
    @SerializedName("ingredients")
    val ingredients: List<IngredientResponse>,
)

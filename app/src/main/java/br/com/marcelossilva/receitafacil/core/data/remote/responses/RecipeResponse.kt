package br.com.marcelossilva.receitafacil.core.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("ownerName")
    val ownerName: String? = null,
    @SerializedName("totalIngredients")
    val totalIngredients: Int,
    @SerializedName("preparationTime")
    val preparationTime: Int,
)

package br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed interface Graphs {

    @Serializable
    data object AuthGraph : Graphs

    @Serializable
    data object HomeGraph : Graphs

}

@Serializable
sealed interface AuthScreens {

    @Serializable
    data object LoginScreen : AuthScreens

    @Serializable
    data object RegisterScreen : AuthScreens

}

@Serializable
sealed interface HomeScreens {

    @Serializable
    data class WebSocketUpdateScreen(val qrCode: String?) : HomeScreens

    @Serializable
    data class RecipeDetailScreen(val recipeId: String) : HomeScreens

    @Serializable
    data class AddRecipeScreen(val recipeId: String? = "") : HomeScreens

    @Serializable
    data object ListRecipesScreen : HomeScreens

    @Serializable
    data object ProfileScreen : HomeScreens

    @Serializable
    data object CreateQrCodeScreen : HomeScreens

    @Serializable
    data object ReadQrCodeScreen : HomeScreens

    @Serializable
    data object UserConnectionsQrCodeScreen : HomeScreens

    @Serializable
    data object SearchScreen : HomeScreens

}


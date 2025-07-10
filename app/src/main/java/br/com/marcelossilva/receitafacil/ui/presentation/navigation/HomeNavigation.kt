package br.com.marcelossilva.receitafacil.ui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeScreen(){
    composable<HomeScreens.HomeScreen> {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
           Text("Home Screen")
        }
    }
}
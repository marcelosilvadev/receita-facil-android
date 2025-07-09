package br.com.marcelossilva.receitafacil.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.RootHost
import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.Graphs
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ReceitaFacilAppTheme {

                val navController: NavHostController = rememberNavController()

                RootHost(
                    startDestination = Graphs.AuthGraph,
                    navController = navController
                )

            }
        }
    }
}
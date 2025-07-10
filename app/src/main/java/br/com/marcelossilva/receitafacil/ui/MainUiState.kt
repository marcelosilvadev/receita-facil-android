package br.com.marcelossilva.receitafacil.ui

import br.com.marcelossilva.receitafacil.ui.presentation.navigation.screens.Graphs

data class MainUiState(
    val startDestination: Graphs = Graphs.AuthGraph
)